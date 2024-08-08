window.addEventListener('load', ()=>{
    var region = document.querySelector('.region');
    var regionOption = document.querySelector('.regionOption');

    region.addEventListener('click', function(e){
        addDisabled(regionOption);
        regionOption.classList.toggle('disabled');
    });
    let searchWord = document.querySelector('input[name="searchWord"]');
    let searchBtn = document.querySelector('#searchBtn');

    //   최초검색
    search();
  
    searchWord.addEventListener('keyup', (e)=>{
        if (searchWord.isEqualNode(e.target)) {
            if (e.key === 'Enter') {
                search();
            }
        }
    })

    searchBtn.addEventListener('click', ()=>{
        search();
    })

    var inputRegion = document.querySelectorAll('input[name="region"]');

    inputRegion.forEach((el) => {
        el.addEventListener('change', (e) => {
            regionOption.classList.add('disabled');
        })
    })

    
})

function fieldSearch(list) {
    const total = document.querySelector('.total');

    if (list == null || list.length < 1) {
        total.innerHTML = '총 0건';
        searchEmpty();
    } else {
        total.innerHTML = '총 ' + list.length + '건';
        createList(list);
    }
}

function search() {
    var region = document.querySelectorAll('input[name="region"]:checked');
    var searchWord = document.querySelector('input[name="searchWord"]');
    let condition = {region : region, word : searchWord};
    fetchPost('/admin/field/get', condition, fieldSearch);
}

function searchEmpty() {
    const searchResult = document.querySelector('#searchResult');

    searchResult.innerHTML = '<li class="none">검색 결과가 없습니다.</li>';
}

function createList(list) {
    const searchResult = document.querySelector('#searchResult');

    let temp = ''
    for (let i=0;i<list.length;i++) {
        temp += resultForm(list[i]);
    }
    searchResult.innerHTML = temp;
}
function resultForm(searchForm) {
    return '<a href="/admin/field/' + searchForm.fieldId + '" class="result">' +
                '<span>' + searchForm.fieldId + '</span>' +
                '<span>' + searchForm.region + '</span>' +
                '<span>' + searchForm.fieldName +'</span>' +
                '<span>' + searchForm.fieldAddress + '</span>' +
                '<span>' + searchForm.createDate + '</span>' +
            '</a>'
}

function addDisabled(e) {
    var optionList = document.querySelectorAll('.subOption');
    for (let i=0;i<optionList.length;i++){
        if (!optionList[i].isEqualNode(e)) {
            optionList[i].classList.add('disabled');
        }
    }
}
