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
 * information: "Portions Copyrighted [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */

package org.forgerock.openam.sts.soap.publish;

import org.forgerock.openam.sts.soap.config.SoapSTSModule;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @see org.forgerock.openam.sts.soap.publish.SoapSTSPublishPoller
 */
public class SoapSTSPublishPollerImpl implements SoapSTSPublishPoller {
    private final ScheduledExecutorService scheduledExecutorService;
    private final SoapSTSInstancePublisher instancePublisher;
    private final int publishServicePollInterval;

    @Inject
    SoapSTSPublishPollerImpl(ScheduledExecutorService scheduledExecutorService,
                             SoapSTSInstancePublisher instancePublisher,
                             @Named(SoapSTSModule.PUBLISH_SERVICE_POLL_INTERVAL_PROPERTY_KEY) int publishServicePollInterval) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.instancePublisher = instancePublisher;
        this.publishServicePollInterval = publishServicePollInterval;
    }

    @Override
    public void initiatePublishPolling() {
        scheduledExecutorService.scheduleAtFixedRate(instancePublisher, publishServicePollInterval, publishServicePollInterval,
                TimeUnit.SECONDS);
    }

    @Override
    public void shutdownPublishPolling() {
        scheduledExecutorService.shutdownNow();
    }
}
