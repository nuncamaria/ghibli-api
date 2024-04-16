package com.nuncamaria.network.exceptions

import java.io.IOException

const val RESOURCE_NOT_FOUND = "Resource not found"

/**
 * Java IOExceptions are Input/Output exceptions (I/O),
 * and they occur whenever an input or output operation is failed or interpreted.
 * For example, if you are trying to read in a file that does not exist, Java would throw an I/O exception.
 */
class ResourceNotFoundException : IOException(RESOURCE_NOT_FOUND)
