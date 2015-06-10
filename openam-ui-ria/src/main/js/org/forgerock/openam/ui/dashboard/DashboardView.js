/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011-2015 ForgeRock AS.
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at
 * http://forgerock.org/license/CDDLv1.0.html
 * See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at http://forgerock.org/license/CDDLv1.0.html
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 */

/*global, define*/
define('org/forgerock/openam/ui/dashboard/DashboardView', [
    'jquery',
    'underscore',
    'org/forgerock/commons/ui/common/main/AbstractView',
    'org/forgerock/openam/ui/dashboard/DeviceManagementView',
    'org/forgerock/openam/ui/dashboard/MyApplicationsView',
    'org/forgerock/openam/ui/dashboard/OAuthTokensView',
    'org/forgerock/openam/ui/dashboard/TrustedDevicesView'
], function ($, _, AbstractView, DeviceManagementView, MyApplicationsView, OAuthTokensView, TrustedDevicesView) {
    var Dashboard = AbstractView.extend({
        template: 'templates/openam/DashboardTemplate.html',
        render: function () {
            this.parentRender(function () {
                MyApplicationsView.render();
                TrustedDevicesView.render();
                OAuthTokensView.render();
                DeviceManagementView.render();
            });
        }
    });

    return new Dashboard();
});
