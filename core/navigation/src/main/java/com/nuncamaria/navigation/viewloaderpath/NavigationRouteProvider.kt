package com.nuncamaria.navigation.viewloaderpath

import android.content.ContentProvider
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri

class NavigationRouteProvider : ContentProvider() {

    @Suppress("TooGenericExceptionThrown")
    override fun onCreate(): Boolean {
        val context = context
        if (context != null) {
            val applicationContext = context.applicationContext
            if (applicationContext != null) {
                try {
                    NavigationRouteLoaderPaths.getInstance(context)?.discoverAndInitialize()
                } catch (exception: PackageManager.NameNotFoundException) {
                    // Ignored to allow add loader of dynamic feature not installed
                }
            } else {
                throw Exception("Application Context cannot be null")
            }
        } else {
            throw Exception("Context cannot be null")
        }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        throw IllegalStateException("Not allowed.")
    }

    override fun getType(uri: Uri): String? {
        throw IllegalStateException("Not allowed.")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw IllegalStateException("Not allowed.")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw IllegalStateException("Not allowed.")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw IllegalStateException("Not allowed.")
    }
}