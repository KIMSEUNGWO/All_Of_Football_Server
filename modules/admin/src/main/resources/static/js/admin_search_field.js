import { Pagination } from "./pagination.js";

const pagination = new Pagination('/admin/field', resultForm);

window.addEventListener('popstate', (e) => {
    if (e.state && e.state.data) {
        pagination.onPopState(e.state.data);
    }
});

window.addEventListener('load', ()=>{
    //   최초검색
    pagination.search();

})

function resultForm(searchForm) {
    return '<a href="/admin/field/' + searchForm.fieldId + '" class="result">' +
                '<span>' + searchForm.fieldId + '</span>' +
                '<span>' + searchForm.region + '</span>' +
                '<span>' + searchForm.title +'</span>' +
                '<span>' + searchForm.address + '</span>' +
            '</a>'
}
