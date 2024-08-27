package org.bms.annotation;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.bms.BmsConstants.BMS_GUEST_API;
import static org.bms.BmsConstants.BMS_GUEST_APIS_FOR_AUTHENTICATED_USERS;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag(name = BMS_GUEST_API, description = BMS_GUEST_APIS_FOR_AUTHENTICATED_USERS)
public @interface BmsGuest {

}
