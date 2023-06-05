function updateCourseModalForm(thisButton, courseName, coursePeriod, courseId) {

    const modal = $('#course-modal');

    // changing course fields
    modal.find('#courseName').val(courseName);
    modal.find('#coursePeriod').val(coursePeriod);
    modal.find('#courseId').val(courseId);

}

/** making courses navbar link active **/
$(document).ready(function(){
    $(".active").removeClass("active");
    $("#courses").addClass("active");
});
