package com.example.moodle.remote

import com.example.moodle.models.Course
import com.example.moodle.models.CourseResponse
import retrofit2.Response
import retrofit2.http.*

interface CourseEndPoints {
    @GET("/course/{id}/grade")
    suspend fun getAllCourses(
        @Path("id") id: Long,
        @Query("courses") courses: Course // just as example
    ) : Response<List<CourseResponse>>

    //example
//    @POST("/createCourse")
//    suspend fun createCourse(
//        @Body course: Course
//    ) : Response<CreateCourseRespone>

}