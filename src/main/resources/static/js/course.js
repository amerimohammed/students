function updateCourseModalForm(thisButton) {

    const modal = $('#course-modal');

    // changing course fields
    modal.find('#courseName').val(thisButton.getAttribute('course-name'));
    modal.find('#coursePeriod').val(thisButton.getAttribute('course-period'));
    modal.find('#courseId').val(thisButton.getAttribute('course-id'));

}

/** making courses navbar link active **/
$(document).ready(function(){
    $(".active").removeClass("active");
    $("#courses").addClass("active");
});
