/*
 * Copyright 2006 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. The Original Code is the Pentaho 
 * BI Platform.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
*/
package org.pentaho.pms.schema.concept.types.fieldtype;

public class FieldTypeSettings
{
    public static final int TYPE_OTHER     = 0; 
    public static final int TYPE_DIMENSION = 1; 
    public static final int TYPE_FACT      = 2;
    public static final int TYPE_KEY       = 3;
    public static final int TYPE_ATTRIBUTE = 4; 
    
    public static final FieldTypeSettings OTHER     = new FieldTypeSettings(TYPE_OTHER);
    public static final FieldTypeSettings DIMENSION = new FieldTypeSettings(TYPE_DIMENSION);
    public static final FieldTypeSettings FACT      = new FieldTypeSettings(TYPE_FACT);
    public static final FieldTypeSettings KEY       = new FieldTypeSettings(TYPE_KEY);
    public static final FieldTypeSettings ATTRIBUTE = new FieldTypeSettings(TYPE_ATTRIBUTE);

    public static final FieldTypeSettings types[] = new FieldTypeSettings[] { OTHER, DIMENSION, FACT, KEY, ATTRIBUTE }; 
    public static final String typeCodes[] = new String[] { "Other", "Dimension", "Fact", "Key", "Attribute" };
    public static final String typeDescriptions[] = new String[] { "Other", "Dimension", "Fact", "Key", "Attribute" };
    
    private int type;

    /**
     * @param type
     */
    public FieldTypeSettings(int type)
    {
        super();
        this.type = type;
    }

    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type)
    {
        this.type = type;
    }

    public boolean isFact()
    {
        return type == TYPE_FACT;
    }
    
    public boolean isDimension()
    {
        return type == TYPE_DIMENSION;
    }
    
    public String toString()
    {
        return typeDescriptions[type];
    }
    
    public String getDescription()
    {
        return toString();
    }
    
    public String getCode()
    {
        return typeCodes[type];
    }

    public boolean equals(Object obj)
    {
        return type==((FieldTypeSettings)obj).getType();
    }
    
    public int hashCode()
    {
        return new Integer(type).hashCode();
    }
    
    public static FieldTypeSettings getType(String description)
    {
        for (int i=0;i<typeDescriptions.length;i++)
        {
            if (typeDescriptions[i].equalsIgnoreCase(description))
            {
                return types[i];
            }
        }
        for (int i=0;i<typeCodes.length;i++)
        {
            if (typeCodes[i].equalsIgnoreCase(description))
            {
                return types[i];
            }
        }
        return OTHER;
    }

    public static FieldTypeSettings guessFieldType(String name)
    {
        String fieldname = name.toLowerCase();
        String ids[] = new String[] { "id", "pk", "tk", "sk" };
        
        // Is it a key field?
        boolean isKey = false;
        for (int i=0;i<ids.length && !isKey;i++)
        {
            if (fieldname.startsWith(ids[i]+"_") || fieldname.endsWith("_"+ids[i])) isKey=true;
        }
        
        if (isKey) return KEY;
        
        return DIMENSION;
    }
}