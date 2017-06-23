
function showDetailModal(id) {
    var detailId = "detail"+id;
    var detailModal = document.getElementById(detailId);
    detailModal.style.display = "block";
}

function showDeleteModal(id) {
    var deleteId = "delete"+id;
    var deleteModal = document.getElementById(deleteId);
    deleteModal.style.display = "block";
}

function removeDetailModal(id) {
    var detailId = "detail"+id;
    var detailModal = document.getElementById(detailId);
    detailModal.style.display = "none";
}

function removeDeleteModal(id) {
    var deleteId = "delete"+id;
    var deleteModal = document.getElementById(deleteId);
    deleteModal.style.display = "none";
}

function hideEditModal() {
    var editModal = document.getElementById('editPopup');
    editModal.style.display = "none";
}

function showErrorModal() {
    var errorModal = document.getElementById('errorPopup');
    errorModal.style.display = "block";
}

function hideErrorModal() {
    var errorModal = document.getElementById('errorPopup');
    errorModal.style.display = "none";
}

var editedTeacher;

function editTeacherAjax(id){
        $.ajax({
            url : "teacher-mgmt-edit",
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(id),
            success : function (response) {
                document.getElementById('emailEdit').value = response.email;
                document.getElementById('firstNameEdit').value = response.firstName;
                document.getElementById('lastNameEdit').value = response.lastName;
                document.getElementById('currentForm').innerHTML = response.formName;
                var editModal = document.getElementById('editPopup');
                editModal.style.display = "block";
                editedTeacher = response;
            }
        });
}

function saveTeacher(){
    editedTeacher.email = document.getElementById('emailEdit').value;
    editedTeacher.firstName = document.getElementById('firstNameEdit').value;
    editedTeacher.lastName = document.getElementById('lastNameEdit').value;
    $.ajax({
        url : "teacher-mgmt-save",
        type : "POST",
        contentType : "application/json",
        data : JSON.stringify(editedTeacher),
        success : function (response) {
            if(response == "Success"){
                window.location.href='/freemarker/teacher-mgmt/teacher-mgmt';
            }
        }
    });
}

function showFormAssignModal(){
    //get available(assignable) forms in school (with no teacher assigned)
    $.ajax({
        url : "teacher-mgmt-get-av-forms",
        type : "GET",
        contentType : "application/json",
        success : function (response) {
            var formAssign = document.getElementById('forms');
            var length = formAssign.options.length;
            for (i = 0; i < length; i++) {
                formAssign.options[i] = null;
            }
            $.each(response, function() {
                var opt = document.createElement('option');
                opt.value = this.id;
                opt.innerHTML = this.name;
                formAssign.appendChild(opt);
            });
            var formAssignModal = document.getElementById('formAssign');
            formAssignModal.style.display = "block";
        }
    });
}

function hideFormAssignModal(){
    var formAssignModal = document.getElementById('formAssign');
    formAssignModal.style.display = "none";
}

function assignFormToTeacher(){
    var formAssignSelect = document.getElementById('forms');
    var selected = formAssignSelect.options[ formAssignSelect.selectedIndex ].value;
    console.log(selected + '111');
    if (!selected || selected == '') {
        return;
    }
    editedTeacher.formId = selected;
    hideFormAssignModal();
}

function removeFormAssignment() {
    editedTeacher.formId = null;
    saveTeacher();
}
