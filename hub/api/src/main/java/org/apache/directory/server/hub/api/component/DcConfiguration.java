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

package org.apache.directory.server.hub.api.component;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


public class DcConfiguration implements Iterable<DcProperty>
{
    private List<DcProperty> properties;
    private Hashtable<String, DcProperty> propertyMap;
    private Integer collectionIndex = null;

    private Hashtable<String, String> attributes = new Hashtable<String, String>();


    public DcConfiguration( List<DcProperty> properties )
    {
        this.properties = properties;
        propertyMap = new Hashtable<String, DcProperty>();

        for ( DcProperty property : properties )
        {
            propertyMap.put( property.getName(), property );
        }
    }


    public DcConfiguration( DcConfiguration configuration )
    {
        properties = new ArrayList<DcProperty>();
        propertyMap = new Hashtable<String, DcProperty>();

        for ( DcProperty prop : configuration )
        {
            addProperty( new DcProperty( prop.getName(), prop.getValue() ) );
        }

        collectionIndex = configuration.getCollectionIndex();
    }


    @Override
    public Iterator<DcProperty> iterator()
    {
        return properties.iterator();
    }


    public void addProperty( DcProperty property )
    {
        DcProperty existing = propertyMap.get( property.getName() );
        if ( existing != null )
        {
            removeProperty( property.getName() );
        }

        properties.add( property );
        propertyMap.put( property.getName(), property );
    }


    public void removeProperty( String propertyName )
    {
        DcProperty removing = propertyMap.remove( propertyName );
        if ( removing != null )
        {
            properties.remove( removing );
        }
    }


    public DcProperty getProperty( String propertyName )
    {
        return propertyMap.get( propertyName );
    }


    public Integer getCollectionIndex()
    {
        return collectionIndex;
    }


    public void setCollectionIndex( Integer collectionIndex )
    {
        this.collectionIndex = collectionIndex;
    }


    public void addAttribute( String name, String value )
    {
        attributes.put( name, value );
    }


    public String getAttribute( String name )
    {
        return attributes.get( name );
    }

}
