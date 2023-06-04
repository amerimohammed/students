function updateTrainingModalForm(thisButton, trainingName, trainingPeriod, trainingId) {

    const modal = $('#training-modal');

    // changing training fields
    modal.find('#trainingName').val(trainingName);
    modal.find('#trainingPeriod').val(trainingPeriod);
    modal.find('#trainingId').val(trainingId);

}

/** making trainings navbar link active **/
$(document).ready(function(){
    $(".active").removeClass("active");
    $("#trainings").addClass("active");
});
