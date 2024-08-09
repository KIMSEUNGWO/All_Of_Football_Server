import { Pagination } from "./pagination.js";

window.addEventListener('load', ()=>{

    const pagination = new Pagination('/admin/field', resultForm);

    var region = document.querySelector('.region');
    var regionOption = document.querySelector('.regionOption');

    region.addEventListener('click', function(e){
        addDisabled(regionOption);
        regionOption.classList.toggle('disabled');
    });
    let searchWord = document.querySelector('input[name="searchWord"]');
    let searchBtn = document.querySelector('#searchBtn');

    //   최초검색
    pagination.search();

    searchWord.addEventListener('keyup', (e)=>{
        if (searchWord.isEqualNode(e.target)) {
            if (e.key === 'Enter') {
                pagination.search();
            }
        }
    })

    searchBtn.addEventListener('click', ()=>{
        pagination.search();
    })

    var inputRegion = document.querySelectorAll('input[name="region"]');

    inputRegion.forEach((el) => {
        el.addEventListener('change', (e) => {
            regionOption.classList.add('disabled');
        })
    })

    
})

function resultForm(searchForm) {
    return '<a href="/admin/field/' + searchForm.fieldId + '" class="result">' +
                '<span>' + searchForm.fieldId + '</span>' +
                '<span>' + searchForm.region + '</span>' +
                '<span>' + searchForm.fieldName +'</span>' +
                '<span>' + searchForm.fieldAddress + '</span>' +
                '<span>' + searchForm.createDate + '</span>' +
            '</a>'
}
