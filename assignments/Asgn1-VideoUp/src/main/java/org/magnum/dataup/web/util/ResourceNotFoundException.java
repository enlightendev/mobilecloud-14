package org.magnum.dataup.web.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by JL25292 on 8/14/2014.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such video")  // 404
public class ResourceNotFoundException extends RuntimeException {

}
