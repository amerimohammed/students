/** making cohorts navbar link active **/
$(document).ready(function () {
    $(".active").removeClass("active");
    $("#cohorts").addClass("active");
});

/** update the values of the fields when we click on edit button **/
function updateCohortModalForm(thisButton) {

    const modal = $('#cohort-modal');

    // changing cohort fields
    modal.find('#cohortId').val(thisButton.getAttribute('cohort-id'))
    modal.find('#cohortName').val(thisButton.getAttribute('cohort-name'));
    modal.find('#cohortStatus').val(thisButton.getAttribute('cohort-status'));
    modal.find('#cohortCourse').val(thisButton.getAttribute('course-id'));

}