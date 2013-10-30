package com.qsoft.eip.common;

import java.io.Serializable;

/**
 * User: Le
 * Date: 10/22/13
 */
public interface IModelContainer
{
    Serializable getModel();

    void setModel(Serializable model);
}
