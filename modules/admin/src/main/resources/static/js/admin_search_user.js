import { PageData, Pagination } from "./pagination.js";

const pagination = new Pagination('/admin/user', resultForm, [
    new PageData('word', () => document.querySelector('input[name="searchWord"]')?.value, (result) => {
        let word = (typeof result === 'object' && result != null) ? result.data.word : result;
        let searchWord = document.querySelector('input[name="searchWord"]');
        searchWord.value = word ?? '';
    })
]);

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
    return `<a href="/admin/user/${searchForm.userId}" class="result">
                <span>${searchForm.userId}</span>
                <span>${searchForm.nickname}</span>
                <span>${searchForm.social}</span>
                <span>${searchForm.sex}</span>
                <span>${dateFormat(searchForm.birth)}</span>
                <span>${dateFormat(searchForm.createDate)} ${timeFormat(searchForm.createDate)}</span>
                <span>${searchForm.status}</span>
            </a>`
}

function dateFormat(data) {
    let date = new Date(data);
    return `${date.getFullYear()}-${leftPad(date.getMonth() + 1)}-${leftPad(date.getDate())}`;
}
function timeFormat(data) {
    let date = new Date(data);
    return `${leftPad(date.getHours())}:${leftPad(date.getMinutes())}:${leftPad(date.getSeconds())}`;
}

function leftPad(data) {
    return String(data).padStart(2, '0');
}
