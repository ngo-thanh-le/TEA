package com.qsoft.eip.common.annotation;

/**
 * User: Le
 * Date: 10/14/13
 */
public enum TransferScope
{
    APPLICATION, // Stored until the application is destroy, the state is saved on destroy even of activity
    WITHIN_ACTIVITY, // Stored within the activity
    SERVICE, // Store on a remote service TODO: default service location TBD
    PERSISTENCE // Store in database
}
