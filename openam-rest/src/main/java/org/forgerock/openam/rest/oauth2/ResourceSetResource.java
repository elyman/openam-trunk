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

package org.forgerock.openam.rest.oauth2;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.forgerock.json.fluent.JsonPointer;
import org.forgerock.json.fluent.JsonValue;
import org.forgerock.json.resource.ActionRequest;
import org.forgerock.json.resource.CollectionResourceProvider;
import org.forgerock.json.resource.CreateRequest;
import org.forgerock.json.resource.DeleteRequest;
import org.forgerock.json.resource.NotSupportedException;
import org.forgerock.json.resource.PatchRequest;
import org.forgerock.json.resource.QueryFilter;
import org.forgerock.json.resource.QueryFilterVisitor;
import org.forgerock.json.resource.QueryRequest;
import org.forgerock.json.resource.QueryResult;
import org.forgerock.json.resource.QueryResultHandler;
import org.forgerock.json.resource.ReadRequest;
import org.forgerock.json.resource.Resource;
import org.forgerock.json.resource.ResourceException;
import org.forgerock.json.resource.ResultHandler;
import org.forgerock.json.resource.ServerContext;
import org.forgerock.json.resource.UpdateRequest;
import org.forgerock.oauth2.resources.ResourceSetDescription;
import org.forgerock.openam.cts.api.fields.ResourceSetTokenField;
import org.forgerock.openam.forgerockrest.entitlements.query.QueryResultHandlerBuilder;
import org.forgerock.openam.rest.resource.RealmContext;
import org.forgerock.util.promise.FailureHandler;
import org.forgerock.util.promise.SuccessHandler;

/**
 * <p>Resource Set resource to expose registered Resource Sets for a given user.</p>
 *
 * <p>Only non-modifiable operations allowed. To alter a Resource Set use the OAuth2 Resource Set Registration
 * endpoint.</p>
 *
 * @since 13.0.0
 */
public class ResourceSetResource implements CollectionResourceProvider {

    private final ResourceSetService resourceSetService;

    /**
     * Constructs a new ResourceSetResource instance.
     *
     * @param resourceSetService An instance of the ResourceSetService.
     */
    @Inject
    public ResourceSetResource(ResourceSetService resourceSetService) {
        this.resourceSetService = resourceSetService;
    }

    /**
     * Not supported.
     *
     * @param context {@inheritDoc}
     * @param request {@inheritDoc}
     * @param handler {@inheritDoc}
     */
    @Override
    public void createInstance(ServerContext context, CreateRequest request, ResultHandler<Resource> handler) {
        handler.handleError(new NotSupportedException());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readInstance(ServerContext context, String resourceId, ReadRequest request,
            final ResultHandler<Resource> handler) {
        String realm = context.asContext(RealmContext.class).getResolvedRealm();
        boolean augmentWithPolicies = request.getFields().isEmpty()
                || request.getFields().contains(new JsonPointer("/policy"));
        resourceSetService.getResourceSet(context, realm, resourceId, augmentWithPolicies)
                .onSuccess(new SuccessHandler<ResourceSetDescription>() {
                    @Override
                    public void handleResult(ResourceSetDescription result) {
                        JsonValue content = getResourceSetJson(result);
                        handler.handleResult(newResource(result.getId(), content));
                    }
                })
                .onFailure(new FailureHandler<ResourceException>() {
                    @Override
                    public void handleError(ResourceException error) {
                        handler.handleError(error);
                    }
                });
    }

    private JsonValue getResourceSetJson(ResourceSetDescription resourceSet) {
        HashMap<String, Object> content = new HashMap<String, Object>(resourceSet.asMap());
        content.put("resourceServer", resourceSet.getClientId());
        if (resourceSet.getPolicy() != null) {
            JsonValue policy = new JsonValue(new HashMap<String, Object>(resourceSet.getPolicy().asMap()));
            policy.remove("policyId");
            policy.remove("name");
            content.put("policy", policy.getObject());
        }
        return new JsonValue(content);
    }

    /**
     * Not supported.
     *
     * @param context {@inheritDoc}
     * @param request {@inheritDoc}
     * @param handler {@inheritDoc}
     */
    @Override
    public void updateInstance(ServerContext context, String resourceId, UpdateRequest request,
            ResultHandler<Resource> handler) {
        handler.handleError(new NotSupportedException());
    }

    /**
     * Not supported.
     *
     * @param context {@inheritDoc}
     * @param request {@inheritDoc}
     * @param handler {@inheritDoc}
     */
    @Override
    public void deleteInstance(ServerContext context, String resourceId, DeleteRequest request,
            ResultHandler<Resource> handler) {
        handler.handleError(new NotSupportedException());
    }

    /**
     * Not supported.
     *
     * @param context {@inheritDoc}
     * @param request {@inheritDoc}
     * @param handler {@inheritDoc}
     */
    @Override
    public void patchInstance(ServerContext context, String resourceId, PatchRequest request,
            ResultHandler<Resource> handler) {
        handler.handleError(new NotSupportedException());
    }

    /**
     * Not supported.
     *
     * @param context {@inheritDoc}
     * @param request {@inheritDoc}
     * @param handler {@inheritDoc}
     */
    @Override
    public void actionCollection(ServerContext context, ActionRequest request, ResultHandler<JsonValue> handler) {
        handler.handleError(new NotSupportedException());
    }

    /**
     * Not supported.
     *
     * @param context {@inheritDoc}
     * @param request {@inheritDoc}
     * @param handler {@inheritDoc}
     */
    @Override
    public void actionInstance(ServerContext context, String resourceId, ActionRequest request,
            ResultHandler<JsonValue> handler) {
        handler.handleError(new NotSupportedException());
    }

    /**
     * Support for querying by equals for /name and /resourceServer with AND only.
     *
     * @param context {@inheritDoc}
     * @param request {@inheritDoc}
     * @param handler {@inheritDoc}
     */
    @Override
    public void queryCollection(final ServerContext context, QueryRequest request, QueryResultHandler handler) {

        final QueryResultHandler queryHandler = QueryResultHandlerBuilder.withPagingAndSorting(handler, request);

        final ResourceSetWithPolicyQuery query;
        try {
            query = request.getQueryFilter().accept(new ResourceSetQueryFilter(), new ResourceSetWithPolicyQuery());
        } catch (UnsupportedOperationException e) {
            handler.handleError(new NotSupportedException(e.getMessage()));
            return;
        }

        String realm = context.asContext(RealmContext.class).getResolvedRealm();
        boolean augmentWithPolicies = request.getFields().isEmpty() || request.getFields().contains(new JsonPointer("/policy"));
        resourceSetService.getResourceSets(context, realm, query, augmentWithPolicies)
                .onSuccess(new SuccessHandler<Collection<ResourceSetDescription>>() {
                    @Override
                    public void handleResult(Collection<ResourceSetDescription> resourceSets) {
                        for (ResourceSetDescription resourceSet : resourceSets) {
                            queryHandler.handleResource(newResource(resourceSet.getId(), getResourceSetJson(resourceSet)));
                        }
                        queryHandler.handleResult(new QueryResult());
                    }
                })
                .onFailure(new FailureHandler<ResourceException>() {
                    @Override
                    public void handleError(ResourceException e) {
                        queryHandler.handleError(e);
                    }
                });
    }

    private Resource newResource(String id, JsonValue content) {
        return new Resource(id, Long.toString(content.hashCode()), content);
    }

    private static final class ResourceSetQueryFilter
            implements QueryFilterVisitor<ResourceSetWithPolicyQuery, ResourceSetWithPolicyQuery> {

        private final Map<JsonPointer, String> queryableFields = new HashMap<JsonPointer, String>();
        private int queryDepth = 0;

        private ResourceSetQueryFilter() {
            queryableFields.put(new JsonPointer("/name"), ResourceSetTokenField.NAME);
            queryableFields.put(new JsonPointer("/resourceServer"), ResourceSetTokenField.CLIENT_ID);
        }

        private void increaseQueryDepth() {
            queryDepth++;
        }

        private void decreaseQueryDepth() {
            queryDepth--;
        }

        @Override
        public ResourceSetWithPolicyQuery visitAndFilter(ResourceSetWithPolicyQuery resourceSetWithPolicyQuery, List<QueryFilter> subFilters) {
            increaseQueryDepth();
            List<org.forgerock.util.query.QueryFilter<String>> childFilters =
                    new ArrayList<org.forgerock.util.query.QueryFilter<String>>();
            for (QueryFilter filter : subFilters) {
                ResourceSetWithPolicyQuery subResourceSetWithPolicyQuery = filter.accept(this, resourceSetWithPolicyQuery);
                if (subResourceSetWithPolicyQuery.getPolicyQuery() != null) {
                    subResourceSetWithPolicyQuery.setOperator("AND");
                } else {
                    childFilters.add(subResourceSetWithPolicyQuery.getResourceSetQuery());
                }
            }
            decreaseQueryDepth();
            resourceSetWithPolicyQuery.setResourceSetQuery(org.forgerock.util.query.QueryFilter.and(childFilters));
            return resourceSetWithPolicyQuery;
        }

        @Override
        public ResourceSetWithPolicyQuery visitEqualsFilter(ResourceSetWithPolicyQuery resourceSetWithPolicyQuery, JsonPointer field, Object valueAssertion) {
            if (new JsonPointer("/policy/permissions/subject").equals(field)) {
                if (queryDepth > 1) {
                    throw new UnsupportedOperationException("Cannot nest queries on /policy/permissions/subject field");
                }
                resourceSetWithPolicyQuery.setPolicyQuery(QueryFilter.equalTo("/permissions/subject", valueAssertion));
            } else {
                resourceSetWithPolicyQuery.setResourceSetQuery(org.forgerock.util.query.QueryFilter.equalTo(verifyFieldIsQueryable(field), valueAssertion));
            }
            return resourceSetWithPolicyQuery;
        }

        @Override
        public ResourceSetWithPolicyQuery visitStartsWithFilter(ResourceSetWithPolicyQuery query, JsonPointer field, Object valueAssertion) {
            if (new JsonPointer("/policy/permissions/subject").equals(field)) {
                if (queryDepth > 1) {
                    throw new UnsupportedOperationException("Cannot nest queries on /policy/permissions/subject field");
                }
                query.setPolicyQuery(QueryFilter.startsWith("/permissions/subject", valueAssertion));
            } else {
                query.setResourceSetQuery(org.forgerock.util.query.QueryFilter.startsWith(verifyFieldIsQueryable(field), valueAssertion));
            }
            return query;
        }

        @Override
        public ResourceSetWithPolicyQuery visitContainsFilter(ResourceSetWithPolicyQuery query, JsonPointer field, Object valueAssertion) {
            if (new JsonPointer("/policy/permissions/subject").equals(field)) {
                if (queryDepth > 1) {
                    throw new UnsupportedOperationException("Cannot nest queries on /policy/permissions/subject field");
                }
                query.setPolicyQuery(QueryFilter.contains("/permissions/subject", valueAssertion));
            } else {
                query.setResourceSetQuery(org.forgerock.util.query.QueryFilter.contains(verifyFieldIsQueryable(field), valueAssertion));
            }
            return query;
        }

        private String verifyFieldIsQueryable(JsonPointer field) {
            if (!queryableFields.containsKey(field)) {
                throw new UnsupportedOperationException("'" + field + "' not queryable");
            } else {
                return queryableFields.get(field);
            }
        }

        @Override
        public ResourceSetWithPolicyQuery visitBooleanLiteralFilter(ResourceSetWithPolicyQuery query, boolean value) {
            throw unsupportedFilterOperation("Boolean Literal");
        }

        @Override
        public ResourceSetWithPolicyQuery visitExtendedMatchFilter(ResourceSetWithPolicyQuery query, JsonPointer field, String operator, Object valueAssertion) {
            throw unsupportedFilterOperation("Extended match");
        }

        @Override
        public ResourceSetWithPolicyQuery visitGreaterThanFilter(ResourceSetWithPolicyQuery query, JsonPointer field, Object valueAssertion) {
            throw unsupportedFilterOperation("Greater than");
        }

        @Override
        public ResourceSetWithPolicyQuery visitGreaterThanOrEqualToFilter(ResourceSetWithPolicyQuery query, JsonPointer field, Object valueAssertion) {
            throw unsupportedFilterOperation("Greater than or equal");
        }

        @Override
        public ResourceSetWithPolicyQuery visitLessThanFilter(ResourceSetWithPolicyQuery query, JsonPointer field, Object valueAssertion) {
            throw unsupportedFilterOperation("Less than");
        }

        @Override
        public ResourceSetWithPolicyQuery visitLessThanOrEqualToFilter(ResourceSetWithPolicyQuery query, JsonPointer field, Object valueAssertion) {
            throw unsupportedFilterOperation("Less than or equal");
        }

        @Override
        public ResourceSetWithPolicyQuery visitNotFilter(ResourceSetWithPolicyQuery query, QueryFilter subFilter) {
            throw unsupportedFilterOperation("Not");
        }

        @Override
        public ResourceSetWithPolicyQuery visitOrFilter(ResourceSetWithPolicyQuery query, List<QueryFilter> subFilters) {
            increaseQueryDepth();
            List<org.forgerock.util.query.QueryFilter<String>> childFilters =
                    new ArrayList<org.forgerock.util.query.QueryFilter<String>>();
            for (QueryFilter filter : subFilters) {
                ResourceSetWithPolicyQuery subResourceSetWithPolicyQuery = filter.accept(this, query);
                if (subResourceSetWithPolicyQuery.getPolicyQuery() != null) {
                    subResourceSetWithPolicyQuery.setOperator("OR");
                } else {
                    childFilters.add(subResourceSetWithPolicyQuery.getResourceSetQuery());
                }
            }
            increaseQueryDepth();
            query.setResourceSetQuery(org.forgerock.util.query.QueryFilter.or(childFilters));
            return query;
        }

        @Override
        public ResourceSetWithPolicyQuery visitPresentFilter(ResourceSetWithPolicyQuery query, JsonPointer field) {
            throw unsupportedFilterOperation("Present");
        }

        private UnsupportedOperationException unsupportedFilterOperation(String filterType) {
            return new UnsupportedOperationException("'" + filterType + "' not supported");
        }

    }
}
