/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */

package org.apache.directory.server.component.handler.ipojo;


import java.util.Hashtable;

import org.apache.felix.ipojo.annotations.Handler;
import org.apache.felix.ipojo.metadata.Element;


@Handler(name = DcHandlerConstants.DSINTERCEPTOR_HANDLER_NAME, namespace = DcHandlerConstants.DSINTERCEPTOR_HANDLER_NS)
public class DirectoryInterceptorHandler extends AbstractDcHandler
{

    @Override
    protected String getHandlerName()
    {
        return DcHandlerConstants.DSINTERCEPTOR_HANDLER_NAME;
    }


    @Override
    protected String getHandlerNamespaceName()
    {
        return DcHandlerConstants.DSINTERCEPTOR_HANDLER_NS;
    }


    @Override
    protected Hashtable<String, String> extractConstantProperties( Element ipojoMetadata )
    {
        Element[] interceptors = ipojoMetadata.getElements( getHandlerName(), getHandlerNamespaceName() );
        // Only one interceptor per class is allowed
        Element interceptor = interceptors[0];

        Hashtable<String, String> constants = new Hashtable<String, String>();

        String interceptionPoint = interceptor.getAttribute( DcHandlerConstants.INTERCEPTOR_INTERCEPTION_POINT );
        String interceptorOperations = interceptor
            .getAttribute( DcHandlerConstants.INTERCEPTOR_INTERCEPTOR_OPERATIONS );

        String isFactory = interceptor.getAttribute( DcHandlerConstants.DSCOMPONENT_FACTORY_PROP_NAME );
        if ( isFactory != null )
        {
            constants.put( DcHandlerConstants.META_IS_FACTORY, isFactory );
        }

        constants.put( DcHandlerConstants.INTERCEPTOR_INTERCEPTION_POINT, interceptionPoint );
        constants.put( DcHandlerConstants.INTERCEPTOR_INTERCEPTOR_OPERATIONS, interceptorOperations );

        return constants;

    }

}
