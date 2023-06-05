/** making cohorts navbar link active **/
$(document).ready(function () {
    $(".active").removeClass("active");
    $("#cohorts").addClass("active");
});

/** update the values of the fields when we click on edit button **/
function updateCohortModalForm(thisButton, cohortId, cohortName, cohortStatus, courseId) {

    const modal = $('#cohort-modal');

    // changing cohort fields
    modal.find('#cohortId').val(cohortId)
    modal.find('#cohortName').val(cohortName);
    modal.find('#cohortStatus').val(cohortStatus);
    modal.find('#cohortCourse').val(courseId);

}