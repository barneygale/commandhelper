/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laytonsmith.core.constructs;

import com.avaje.ebeaninternal.server.persist.BindValues;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.Exceptions;
import com.laytonsmith.core.natives.interfaces.ArrayAccess;

/**
 *
 * @author Layton
 */
public class CString extends Construct implements Cloneable, ArrayAccess{
    
    public CString(String value, Target t){
        super(value, ConstructType.STRING, t);
    }
    
    public CString(char value, Target t){
        this(Character.toString(value), t);
    }
    
    public CString(CharSequence value, Target t){
        this(value.toString(), t);
    }
    
    @Override
    public CString clone() throws CloneNotSupportedException{
        return this;
    }

    @Override
    public boolean isDynamic() {
        return false;
    }

    public Construct get(String index, Target t) {
        try{
            int i = (int)Integer.parseInt(index);
            return new CString(this.val().charAt(i), t);
        } catch(NumberFormatException e){
            throw new ConfigRuntimeException("Expecting numerical index, but recieved " + index, Exceptions.ExceptionType.FormatException, t);
        }
    }

    public int size() {
        return val().length();
    }

    public boolean canBeAssociative() {
        return false;
    }
}
