window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const movieTable = document.getElementById('movieTable');
    const tvProgramTable = document.getElementById('tvProgramTable');
    const boardTable = document.getElementById('boardTable');
    const memberTable = document.getElementById('memberTable');

    if (movieTable) {
        new simpleDatatables.DataTable(movieTable);
    }

    if (tvProgramTable) {
        new simpleDatatables.DataTable(tvProgramTable);
    }
    
    if (boardTable) {
        new simpleDatatables.DataTable(boardTable);
    }
     if (memberTable) {
        new simpleDatatables.DataTable(memberTable);
    }
});