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


function onChange() {
    var x = document.getElementById("mySelect").value;
    document.getElementById("use").submit(x);
}

