/**
 * Created by User on 26.06.2017.
 */
function showDetailModal(id) {
    var detailId = "detail"+id;
    var detailModal = document.getElementById(detailId);
    detailModal.style.display = "block";

}

function removeDetailModal(id) {
    var detailId = "detail"+id;
    var detailModal = document.getElementById(detailId);
    detailModal.style.display = "none";
}
function showDisableModal(id) {
    var deleteId = "delete"+id;
    var deleteModal = document.getElementById(deleteId);
    deleteModal.style.display = "block";
}
function removeDisableModal(id) {
    var deleteId = "delete"+id;
    var deleteModal = document.getElementById(deleteId);
    deleteModal.style.display = "none";
}
function showEnableModal(id) {
    var deleteId = "delete"+id;
    var deleteModal = document.getElementById(deleteId);
    deleteModal.style.display = "block";
}
function removeEnableModal(id) {
    var deleteId = "delete"+id;
    var deleteModal = document.getElementById(deleteId);
    deleteModal.style.display = "none";
}

function onChange() {
    var x = document.getElementById("mySelect").value;
    document.getElementById("use").submit(x);
}

