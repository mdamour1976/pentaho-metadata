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
package org.pentaho.pms.schema.concept.types.number;

import java.math.BigDecimal;

import org.pentaho.pms.schema.concept.ConceptPropertyInterface;
import org.pentaho.pms.schema.concept.types.ConceptPropertyBase;
import org.pentaho.pms.schema.concept.types.ConceptPropertyType;

public class ConceptPropertyNumber extends ConceptPropertyBase implements ConceptPropertyInterface, Cloneable
{
    private BigDecimal value;
    
    public ConceptPropertyNumber(String name, BigDecimal value)
    {
        super(name);
        this.value = value;
    }

    public ConceptPropertyNumber(String name, double value)
    {
        super(name);
        this.value = new BigDecimal(value);
    }

    public ConceptPropertyNumber(String name, long value)
    {
        this(name, (double)value);
    }

    public ConceptPropertyNumber(String name, int value)
    {
        this(name, (double)value);
    }

    public ConceptPropertyNumber(String name, byte value)
    {
        this(name, (double)value);
    }

    public String toString()
    {
        if (value==null) return null;
        return value.toString();
    }
    
    public Object clone() throws CloneNotSupportedException
    {
      ConceptPropertyNumber rtn = (ConceptPropertyNumber) super.clone();
      if (value != null) {
        rtn.value = new BigDecimal(value.toString());
      }
      return rtn;
    }

    public ConceptPropertyType getType()
    {
        return ConceptPropertyType.NUMBER;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = (BigDecimal) value;
    }
    
    public boolean equals(Object obj)
    {
        return value.equals((BigDecimal)obj);
    }

    public int hashCode()
    {
        return value.hashCode();
    }
}