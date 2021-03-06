/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */
package org.forgerock.openam.forgerockrest.entitlements;

import static org.fest.assertions.Assertions.assertThat;
import static org.forgerock.json.fluent.JsonValue.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.sun.identity.entitlement.EntitlementException;
import com.sun.identity.shared.debug.Debug;
import org.forgerock.json.fluent.JsonValue;
import org.forgerock.json.resource.Context;
import org.forgerock.json.resource.CreateRequest;
import org.forgerock.json.resource.DeleteRequest;
import org.forgerock.json.resource.QueryRequest;
import org.forgerock.json.resource.QueryResult;
import org.forgerock.json.resource.QueryResultHandler;
import org.forgerock.json.resource.ReadRequest;
import org.forgerock.json.resource.Resource;
import org.forgerock.json.resource.ResourceException;
import org.forgerock.json.resource.ResultHandler;
import org.forgerock.json.resource.SecurityContext;
import org.forgerock.json.resource.ServerContext;
import org.forgerock.json.resource.UpdateRequest;
import org.forgerock.json.resource.servlet.HttpContext;
import org.forgerock.openam.entitlement.ResourceType;
import org.forgerock.openam.entitlement.service.ResourceTypeService;
import org.forgerock.openam.entitlement.utils.EntitlementUtils;
import org.forgerock.openam.forgerockrest.guice.ForgerockRestGuiceModule;
import org.forgerock.openam.rest.query.QueryException;
import org.forgerock.openam.rest.resource.RealmContext;
import org.forgerock.openam.rest.resource.SSOTokenContext;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.security.auth.Subject;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ResourceTypesResourceTest {

    private final String jsonResourceType =
            "{" +
            "    \"actions\": {" +
            "            \"ACTION\": true," +
            "            \"CREATE\": true," +
            "            \"DELETE\": true," +
            "            \"PATCH\": true," +
            "            \"QUERY\": true," +
            "            \"READ\": true," +
            "            \"UPDATE\": true" +
            "    }," +
            "    \"description\": \"An example resource type\"," +
            "    \"name\": \"myResourceType\"," +
            "    \"patterns\": [" +
            "            \"http://example.com:80/*\"" +
            "    ]" +
            "}" +
            ";";

    private final Map<String, Set<String>> rawData = new HashMap<String, Set<String>>();

    private ResultHandler<Resource> mockResultHandler;

    private ServerContext mockServerContext;
    private ResourceTypesResource resourceTypesResource;
    private ResourceTypeService resourceTypeService;
    private Subject callerSubject;

    private abstract class MockResourceTypeService implements ResourceTypeService {
        ResourceType resourceType;

        @Override
        public ResourceType saveResourceType(Subject subject, String realm, ResourceType resourceType)
                throws EntitlementException {
            this.resourceType = resourceType;

            return resourceType;
        }

        @Override
        public ResourceType getResourceType(Subject subject, String realm, String uuid) throws EntitlementException {
            assertThat(uuid).isEqualTo(resourceType.getUUID());

            return resourceType;
        }

        @Override
        public ResourceType updateResourceType(Subject subject, String realm, ResourceType resourceType)
                throws EntitlementException {
            assertThat(this.resourceType.getUUID()).isEqualTo(resourceType.getUUID());

            return resourceType;
        }

        @Override
        public void deleteResourceType(Subject subject, String realm, String uuid) throws EntitlementException {
            assertThat(uuid).isEqualTo(resourceType.getUUID());
        }
    }

    // this is here to remove compiler warnings for ResultHandler<Resource> = mock(ResultHandler.class)
    interface ResourceResultHandler extends ResultHandler<Resource> {}

    @BeforeMethod
    public void setUp() throws ResourceException {

        callerSubject = new Subject();

        // to mock the HTTP method, we need the following contexts
        Context httpContext = new HttpContext(json(object(
                field(HttpContext.ATTR_HEADERS, Collections.singletonMap("method", Arrays.asList("PUT"))),
                field(HttpContext.ATTR_PARAMETERS, Collections.emptyMap()))), null);

        Context securityContext = new SecurityContext(httpContext, null, null);

        Context subjectContext = new SSOTokenContext(securityContext) {
            @Override
            public Subject getCallerSubject() {
                return callerSubject;
            }
        };

        RealmContext realmContext = new RealmContext(subjectContext);
        realmContext.addSubRealm("/", "/");

        mockServerContext = new ServerContext(realmContext);

        resourceTypeService = mock(MockResourceTypeService.class);

        Debug debug = mock(Debug.class);
        resourceTypesResource = new ResourceTypesResource(debug,
                new EntitlementsExceptionMappingHandler(ForgerockRestGuiceModule.getEntitlementsErrorHandlers()),
                resourceTypeService);

        mockResultHandler = mock(ResourceResultHandler.class);

        rawData.put("name", Collections.singleton("myResourceType"));
        rawData.put("description", Collections.singleton("myResourceType"));
        rawData.put("realm", Collections.singleton("/"));
        rawData.put("actions", Collections.singleton("CREATE"));
        rawData.put("patterns", Collections.singleton("http://example.com:80/*"));
        rawData.put("creationDate", Collections.singleton(String.valueOf(new Date().getTime())));
        rawData.put("lastModifiedDate", Collections.singleton(String.valueOf(new Date().getTime())));
    }

    @Test
    public void shouldCreateResourceType() throws EntitlementException {

        //given
        CreateRequest mockCreateRequest = mock(CreateRequest.class);
        JsonValue content = mock(JsonValue.class);

        given(mockCreateRequest.getContent()).willReturn(content);
        given(content.toString()).willReturn(jsonResourceType);

        doCallRealMethod().when(resourceTypeService).saveResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.createInstance(mockServerContext, mockCreateRequest, mockResultHandler);

        //then
        verify(mockResultHandler, times(0)).handleError(any(ResourceException.class));

        ArgumentCaptor<Resource> resourceCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(mockResultHandler, times(1)).handleResult(resourceCaptor.capture());
        assertThat(resourceCaptor.getValue().getContent().get("name").asString()).isEqualTo("myResourceType");
    }

    @Test
    public void createShouldFailIfCallerSubjectNotPresent() {

        //given
        CreateRequest mockCreateRequest = mock(CreateRequest.class);

        // subject is null, which will represent a broken SSOTokenContext
        callerSubject = null;

        //when
        resourceTypesResource.createInstance(mockServerContext, mockCreateRequest, mockResultHandler);

        //then
        ArgumentCaptor<ResourceException> captor = ArgumentCaptor.forClass(ResourceException.class);
        verify(mockResultHandler, times(1)).handleError(captor.capture());
        ResourceException e = captor.getValue();
        assertThat(e.getCode()).isEqualTo(ResourceException.INTERNAL_ERROR);

        assertThat(e.getCause()).isInstanceOf(EntitlementException.class);
        assertThat(((EntitlementException) e.getCause()).getErrorCode()).isEqualTo(EntitlementException.INTERNAL_ERROR);
    }

    @Test
    public void createShouldFailIfJsonResourceTypeInvalid() throws EntitlementException {
       //given
        JsonValue content = mock(JsonValue.class);

        CreateRequest mockCreateRequest = mock(CreateRequest.class);
        given(mockCreateRequest.getContent()).willReturn(content);

        // the name attribute is required, but here replaced by an unrecognised attribute
        given(content.toString()).willReturn(jsonResourceType.replaceAll("\"name\"", "\"id\""));
        doCallRealMethod().when(resourceTypeService).saveResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.createInstance(mockServerContext, mockCreateRequest, mockResultHandler);

        //then
        ArgumentCaptor<ResourceException> errorCaptor = ArgumentCaptor.forClass(ResourceException.class);
        verify(mockResultHandler, times(1)).handleError(errorCaptor.capture());

        ResourceException e = errorCaptor.getValue();
        assertThat(e.getCode()).isEqualTo(ResourceException.BAD_REQUEST);

        assertThat(e.getCause()).isInstanceOf(EntitlementException.class);
        assertThat(((EntitlementException)e.getCause()).getErrorCode()).isEqualTo(EntitlementException.INVALID_CLASS);

        verify(mockResultHandler, times(0)).handleResult(any(Resource.class));
    }

    /*
     * This seems to throw an unchecked exception (NPE) when the name is not present
     */
    @Test
    public void createShouldFailIfResourceTypeNameAbsent() throws EntitlementException {
       //given
        JsonValue content = mock(JsonValue.class);

        CreateRequest mockCreateRequest = mock(CreateRequest.class);
        given(mockCreateRequest.getContent()).willReturn(content);

        // the name attribute is required, but here replaced by an unrecognised attribute
        given(content.toString()).willReturn(jsonResourceType.replaceAll("\"name\":.*,", ""));
        doCallRealMethod().when(resourceTypeService).saveResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.createInstance(mockServerContext, mockCreateRequest, mockResultHandler);

        //then
        ArgumentCaptor<ResourceException> errorCaptor = ArgumentCaptor.forClass(ResourceException.class);
        verify(mockResultHandler, times(1)).handleError(errorCaptor.capture());

        ResourceException e = errorCaptor.getValue();
        assertThat(e.getCode()).isEqualTo(ResourceException.BAD_REQUEST);

        assertThat(e.getCause()).isInstanceOf(EntitlementException.class);
        assertThat(((EntitlementException)e.getCause()).getErrorCode()).isEqualTo(EntitlementException.MISSING_RESOURCE_TYPE_NAME);

        verify(mockResultHandler, times(0)).handleResult(any(Resource.class));
    }

    /*
     * This expected failure when the newResourceId in the create request is not the resource type name.
     *
     * Now the new resource id is not used.
     *
     */
    @Test
    public void createShouldIgnoreNewResourceId() throws EntitlementException {
        //given
        CreateRequest mockCreateRequest = mock(CreateRequest.class);
        JsonValue content = mock(JsonValue.class);
        given(mockCreateRequest.getContent()).willReturn(content);

        // the resource ID in the request is differs form anything in the JSON resource type
        given(mockCreateRequest.getNewResourceId()).willReturn("ignored-new-resource-id");
        given(content.toString()).willReturn(jsonResourceType);

        doCallRealMethod().when(resourceTypeService).saveResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.createInstance(mockServerContext, mockCreateRequest, mockResultHandler);

        //then
        verify(mockResultHandler, times(0)).handleError(any(ResourceException.class));
        verify(mockResultHandler, times(1)).handleResult(any(Resource.class));
    }

    /*
     * A UUID is supplied on create, but it is ignored
     */
    @Test
    public void createShouldIgnoreUUIDInJson() throws EntitlementException {
        //given
        CreateRequest mockCreateRequest = mock(CreateRequest.class);
        JsonValue content = mock(JsonValue.class);

        given(mockCreateRequest.getContent()).willReturn(content);
        given(mockCreateRequest.getNewResourceId()).willReturn(null);

        String jsonWithUuid =
                "{" +
                        "    \"actions\": {" +
                        "            \"ACTION\": true," +
                        "            \"CREATE\": true," +
                        "            \"DELETE\": true," +
                        "            \"PATCH\": true," +
                        "            \"QUERY\": true," +
                        "            \"READ\": true," +
                        "            \"UPDATE\": true" +
                        "    }," +
                        "    \"description\": \"An example resource type\"," +
                        "    \"uuid\": \"123.456.789\"," +
                        "    \"name\": \"myResourceType\"," +
                        "    \"patterns\": [" +
                        "            \"http://example.com:80/*\"" +
                        "    ]" +
                        "}" +
                        ";";

        // Json has unnecessary UUID
        given(content.toString()).willReturn(jsonWithUuid);

        doCallRealMethod().when(resourceTypeService).saveResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.createInstance(mockServerContext, mockCreateRequest, mockResultHandler);

        //then
        verify(mockResultHandler, times(0)).handleError(any(ResourceException.class));
        verify(mockResultHandler, times(1)).handleResult(any(Resource.class));
    }

    @Test
    public void createShouldHandleResourceTypeServiceFailure() throws EntitlementException {
        //given
        CreateRequest mockCreateRequest = mock(CreateRequest.class);
        JsonValue content = mock(JsonValue.class);

        given(mockCreateRequest.getContent()).willReturn(content);
        given(mockCreateRequest.getNewResourceId()).willReturn(null);

        given(content.toString()).willReturn(jsonResourceType);

        // simulate failure in the resource type service
        Throwable t = new EntitlementException(EntitlementException.RESOURCE_TYPE_ALREADY_EXISTS);
        Mockito.doThrow(t).when(resourceTypeService).saveResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.createInstance(mockServerContext, mockCreateRequest, mockResultHandler);

        //then
        ArgumentCaptor<ResourceException> errorCaptor = ArgumentCaptor.forClass(ResourceException.class);
        verify(mockResultHandler, times(1)).handleError(errorCaptor.capture());
        ResourceException e = errorCaptor.getValue();

        assertThat(e.getCode()).isEqualTo(ResourceException.CONFLICT);

        assertThat(e.getCause()).isInstanceOf(EntitlementException.class);
        assertThat(((EntitlementException)e.getCause()).getErrorCode()).isEqualTo(EntitlementException.RESOURCE_TYPE_ALREADY_EXISTS);

        verify(mockResultHandler, times(0)).handleResult(any(Resource.class));
    }

    private Resource setupExistingResourceTypeFromJson(String jsonResourceType) throws EntitlementException {
        //given
        CreateRequest createRequest = mock(CreateRequest.class);
        JsonValue requestContent = mock(JsonValue.class);
        given(createRequest.getContent()).willReturn(requestContent);
        given(requestContent.toString()).willReturn(jsonResourceType);

        ResultHandler<Resource> createHandler = mock(ResourceResultHandler.class);
        doCallRealMethod().when(resourceTypeService).saveResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.createInstance(mockServerContext, createRequest, createHandler);

        //then
        verify(createHandler, times(0)).handleError(any(ResourceException.class));

        ArgumentCaptor<Resource> createCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(createHandler, times(1)).handleResult(createCaptor.capture());
        return createCaptor.getValue();
    }

    @Test
    public void shouldReadResourceTypes() throws EntitlementException {
        //given
        Resource createdResource = setupExistingResourceTypeFromJson(jsonResourceType);

        ReadRequest readRequest = mock(ReadRequest.class);
        when(resourceTypeService.getResourceType(any(Subject.class), anyString(), anyString())).thenCallRealMethod();

        //when
        resourceTypesResource.readInstance(mockServerContext, createdResource.getId(), readRequest, mockResultHandler);

        //then
        verify(mockResultHandler, times(0)).handleError(any(ResourceException.class));
        verify(mockResultHandler, times(1)).handleResult(any(Resource.class));
    }

    @Test
    public void readShouldHandleServiceException() throws EntitlementException {
        //given
        Resource createdResource = setupExistingResourceTypeFromJson(jsonResourceType);

        ReadRequest readRequest = mock(ReadRequest.class);
        Throwable t = new EntitlementException(EntitlementException.RESOURCE_TYPE_ALREADY_EXISTS);
        when(resourceTypeService.getResourceType(any(Subject.class), anyString(), anyString())).thenThrow(t);

        //when
        resourceTypesResource.readInstance(mockServerContext, createdResource.getId(), readRequest, mockResultHandler);

        //then
        ArgumentCaptor<ResourceException> errorCaptor = ArgumentCaptor.forClass(ResourceException.class);
        verify(mockResultHandler, times(1)).handleError(errorCaptor.capture());
        ResourceException e = errorCaptor.getValue();

        assertThat(e.getCode()).isEqualTo(ResourceException.CONFLICT);

        assertThat(e.getCause()).isInstanceOf(EntitlementException.class);
        assertThat(((EntitlementException)e.getCause()).getErrorCode()).isEqualTo(EntitlementException.RESOURCE_TYPE_ALREADY_EXISTS);

        verify(mockResultHandler, times(0)).handleResult(any(Resource.class));
    }

    @Test
    public void shouldUpdateResourceTypes() throws EntitlementException {
        //given
        Resource createdResource = setupExistingResourceTypeFromJson(jsonResourceType);

        UpdateRequest updateRequest = mock(UpdateRequest.class);
        given(updateRequest.getContent()).willReturn(createdResource.getContent());
        doCallRealMethod().when(resourceTypeService).updateResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.updateInstance(mockServerContext, createdResource.getId(), updateRequest, mockResultHandler);

        //then
        verify(mockResultHandler, times(0)).handleError(any(ResourceException.class));
        verify(mockResultHandler, times(1)).handleResult(any(Resource.class));
    }

    @Test
    public void shouldUpdateResourceTypeName() throws EntitlementException {
        //given
        Resource createdResource = setupExistingResourceTypeFromJson(jsonResourceType);

        JsonValue v = createdResource.getContent();
        v.remove("name");
        v.add("name", "modifiedName");

        UpdateRequest updateRequest = mock(UpdateRequest.class);

        given(updateRequest.getContent()).willReturn(v);
        doCallRealMethod().when(resourceTypeService).updateResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.updateInstance(mockServerContext, createdResource.getId(), updateRequest, mockResultHandler);

        //then
        verify(mockResultHandler, times(0)).handleError(any(ResourceException.class));

        ArgumentCaptor<Resource> updateCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(mockResultHandler, times(1)).handleResult(updateCaptor.capture());
        Resource updatedResource = updateCaptor.getValue();

        assertThat(updatedResource.getContent().get("name").asString()).isEqualTo("modifiedName");
    }

    @Test
    public void updateShouldHandleServiceException() throws EntitlementException {
        //given
        Resource createdResource = setupExistingResourceTypeFromJson(jsonResourceType);

        UpdateRequest updateRequest = mock(UpdateRequest.class);
        given(updateRequest.getContent()).willReturn(createdResource.getContent());
        Throwable t = new EntitlementException(EntitlementException.RESOURCE_TYPE_ALREADY_EXISTS);
        doThrow(t).when(resourceTypeService).updateResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.updateInstance(mockServerContext, createdResource.getId(), updateRequest, mockResultHandler);

        //then
        ArgumentCaptor<ResourceException> errorCaptor = ArgumentCaptor.forClass(ResourceException.class);
        verify(mockResultHandler, times(1)).handleError(errorCaptor.capture());
        ResourceException e = errorCaptor.getValue();

        assertThat(e.getCode()).isEqualTo(ResourceException.CONFLICT);

        assertThat(e.getCause()).isInstanceOf(EntitlementException.class);
        assertThat(((EntitlementException)e.getCause()).getErrorCode()).isEqualTo(EntitlementException.RESOURCE_TYPE_ALREADY_EXISTS);

        verify(mockResultHandler, times(0)).handleResult(any(Resource.class));
    }

    @Test
    public void updateShouldFailWhenJsonInvalid() throws EntitlementException {
        //given
        Resource createdResource = setupExistingResourceTypeFromJson(jsonResourceType);

        // modify value created to contain invalid Json resource type
        JsonValue modifiedValue = createdResource.getContent();
        modifiedValue.remove("name");
        modifiedValue.add("x-y", "z");

        UpdateRequest updateRequest = mock(UpdateRequest.class);
        given(updateRequest.getContent()).willReturn(modifiedValue);

        doCallRealMethod().when(resourceTypeService).updateResourceType(any(Subject.class), anyString(),
                any(ResourceType.class));

        //when
        resourceTypesResource.updateInstance(mockServerContext, createdResource.getId(), updateRequest, mockResultHandler);

        //then
        ArgumentCaptor<ResourceException> errorCaptor = ArgumentCaptor.forClass(ResourceException.class);
        verify(mockResultHandler, times(1)).handleError(errorCaptor.capture());
        ResourceException e = errorCaptor.getValue();

        assertThat(e.getCode()).isEqualTo(ResourceException.BAD_REQUEST);

        assertThat(e.getCause()).isInstanceOf(EntitlementException.class);
        assertThat(((EntitlementException)e.getCause()).getErrorCode()).isEqualTo(EntitlementException.INVALID_CLASS);

        verify(mockResultHandler, times(0)).handleResult(any(Resource.class));
    }

    @Test
    public void shouldDeleteResourceTypes() throws EntitlementException {
        //Given
        Resource createdResource = setupExistingResourceTypeFromJson(jsonResourceType);

        DeleteRequest mockDeleteRequest = mock(DeleteRequest.class);
        doCallRealMethod().when(resourceTypeService).deleteResourceType(any(Subject.class), anyString(), anyString());

        //when
        resourceTypesResource.deleteInstance(mockServerContext, createdResource.getId(), mockDeleteRequest, mockResultHandler);

        //then
        verify(mockResultHandler, times(0)).handleError(any(ResourceException.class));

        ArgumentCaptor<Resource> deleteCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(mockResultHandler, times(1)).handleResult(deleteCaptor.capture());

        assertThat(deleteCaptor.getValue().getContent().keys()).isEmpty();
        assertThat(deleteCaptor.getValue().getId()).isEqualTo(createdResource.getId());
    }

    @Test
    public void deleteShouldHandleServiceException() throws EntitlementException {
        //Given
        Resource createdResource = setupExistingResourceTypeFromJson(jsonResourceType);

        DeleteRequest deleteRequest = mock(DeleteRequest.class);
        Throwable t = new EntitlementException(EntitlementException.RESOURCE_TYPE_ALREADY_EXISTS);
        doThrow(t).when(resourceTypeService).deleteResourceType(any(Subject.class), anyString(), anyString());

        //when
        resourceTypesResource.deleteInstance(mockServerContext, createdResource.getId(), deleteRequest, mockResultHandler);

        //then
        ArgumentCaptor<ResourceException> errorCaptor = ArgumentCaptor.forClass(ResourceException.class);
        verify(mockResultHandler, times(1)).handleError(errorCaptor.capture());
        ResourceException e = errorCaptor.getValue();

        assertThat(e.getCode()).isEqualTo(ResourceException.CONFLICT);

        assertThat(e.getCause()).isInstanceOf(EntitlementException.class);
        assertThat(((EntitlementException)e.getCause()).getErrorCode()).isEqualTo(EntitlementException.RESOURCE_TYPE_ALREADY_EXISTS);

        verify(mockResultHandler, times(0)).handleResult(any(Resource.class));
    }

    @Test
    public void queryShouldHandleAllResults() throws EntitlementException {
        //given
        QueryRequest queryRequest = mock(QueryRequest.class);
        QueryResultHandler queryHandler = mock(QueryResultHandler.class);

        Map<String, Map<String, Set<String>>> resourceTypes = new HashMap<String, Map<String, Set<String>>>();
        final int resultSize = 10;
        for (int i = 0; i < resultSize; i++) {
            resourceTypes.put(UUID.randomUUID().toString(), rawData);
        }

        when(resourceTypeService.getResourceTypesData(any(Subject.class), anyString())).thenReturn(resourceTypes);
        when(resourceTypeService.getResourceType(any(Subject.class), anyString(), anyString())).thenReturn
                (EntitlementUtils.resourceTypeFromMap(UUID.randomUUID().toString(), rawData));
        when(queryHandler.handleResource(any(Resource.class))).thenReturn(true);

        //when
        resourceTypesResource.queryCollection(mockServerContext, queryRequest, queryHandler);

        //then
        verify(queryHandler, times(0)).handleError(any(ResourceException.class));

        verify(queryHandler, times(resultSize)).handleResource(any(Resource.class));

        ArgumentCaptor<QueryResult> resultCaptor = ArgumentCaptor.forClass(QueryResult.class);
        verify(queryHandler, times(1)).handleResult(resultCaptor.capture());
        QueryResult result = resultCaptor.getValue();

        assertThat(result.getRemainingPagedResults()).isEqualTo(0);
    }

   @Test
    public void queryShouldPageResults() throws EntitlementException {
        //given
        QueryRequest queryRequest = mock(QueryRequest.class);
        QueryResultHandler queryHandler = mock(QueryResultHandler.class);

        final int firstPageSize = 2;
        final int pageOffset = 0;
        final int resultSize = 10;
        Map<String, Map<String, Set<String>>> resourceTypes = new HashMap<String, Map<String, Set<String>>>();
        for (int i = 0; i < resultSize; i++) {
           resourceTypes.put(UUID.randomUUID().toString(), rawData);
        }

        when(resourceTypeService.getResourceTypesData(any(Subject.class), anyString())).thenReturn(resourceTypes);
        when(resourceTypeService.getResourceType(any(Subject.class), anyString(), anyString())).thenReturn
               (EntitlementUtils.resourceTypeFromMap(UUID.randomUUID().toString(), rawData));
        when(queryRequest.getPagedResultsOffset()).thenReturn(pageOffset);
        when(queryRequest.getPageSize()).thenReturn(firstPageSize);

        Answer<Boolean> onlyFirstPage = new Answer<Boolean>() {
            int count = 0;

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                return count++ < firstPageSize;
            }
        };

        when(queryHandler.handleResource(any(Resource.class))).thenAnswer(onlyFirstPage);

        //when
        resourceTypesResource.queryCollection(mockServerContext, queryRequest, queryHandler);

        //then
        verify(queryHandler, times(0)).handleError(any(ResourceException.class));

        verify(queryHandler, times(firstPageSize)).handleResource(any(Resource.class));

        ArgumentCaptor<QueryResult> resultCaptor = ArgumentCaptor.forClass(QueryResult.class);
        verify(queryHandler, times(1)).handleResult(resultCaptor.capture());
        QueryResult result = resultCaptor.getValue();

        assertThat(result.getRemainingPagedResults()).isEqualTo(resultSize - firstPageSize);
    }

    @Test
    public void shouldHandleQueryPageLargerThanResults() throws EntitlementException {
        //given
        QueryRequest queryRequest = mock(QueryRequest.class);
        QueryResultHandler queryHandler = mock(QueryResultHandler.class);

        final int lastPageSize = 5;
        final int pageOffset = 10;
        final int resultSize = 11;
        Map<String, Map<String, Set<String>>> resourceTypes = new HashMap<String, Map<String, Set<String>>>();
        for (int i = 0; i < resultSize; i++) {
            resourceTypes.put(UUID.randomUUID().toString(), rawData);
        }

        when(resourceTypeService.getResourceTypesData(any(Subject.class), anyString())).thenReturn(resourceTypes);
        when(resourceTypeService.getResourceType(any(Subject.class), anyString(), anyString())).thenReturn
                (EntitlementUtils.resourceTypeFromMap(UUID.randomUUID().toString(), rawData));
        when(queryRequest.getPagedResultsOffset()).thenReturn(pageOffset);
        when(queryRequest.getPageSize()).thenReturn(lastPageSize);

        Answer<Boolean> onlyFirstPage = new Answer<Boolean>() {
            int count = pageOffset;

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                return count++ < resultSize;
            }
        };

        when(queryHandler.handleResource(any(Resource.class))).thenAnswer(onlyFirstPage);

        //when
        resourceTypesResource.queryCollection(mockServerContext, queryRequest, queryHandler);

        //then
        verify(queryHandler, times(0)).handleError(any(ResourceException.class));

        verify(queryHandler, times(resultSize - pageOffset)).handleResource(any(Resource.class));

        ArgumentCaptor<QueryResult> resultCaptor = ArgumentCaptor.forClass(QueryResult.class);
        verify(queryHandler, times(1)).handleResult(resultCaptor.capture());
        QueryResult result = resultCaptor.getValue();

        assertThat(result.getRemainingPagedResults()).isEqualTo(0);
    }


    @Test
    public void queryShouldHandleQueryException() throws EntitlementException {
        //given
        setupExistingResourceTypeFromJson(jsonResourceType);

        QueryRequest queryRequest = mock(QueryRequest.class);
        QueryResultHandler queryHandler = mock(QueryResultHandler.class);
        Throwable t = new QueryException(QueryException.QueryErrorCode.FILTER_BOOLEAN_LITERAL_FALSE);
        when(resourceTypeService.getResourceTypesData(any(Subject.class), anyString())).thenThrow(t);

        //when
        resourceTypesResource.queryCollection(mockServerContext, queryRequest, queryHandler);

        //then
        verify(queryHandler, times(0)).handleResult(any(QueryResult.class));
        verify(queryHandler, times(1)).handleError(any(ResourceException.class));
    }

}

