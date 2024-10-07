import { PageData, Pagination } from "./pagination.js";

const pagination = new Pagination('/admin/match', resultForm, [
    new PageData('word', () => document.querySelector('input[name="searchWord"]')?.value, (result) => {
        let word = (typeof result === 'object' && result != null) ? result.data.word : result;
        let searchWord = document.querySelector('input[name="searchWord"]');
        searchWord.value = word ?? '';
    }), 
    new PageData('region', () => document.querySelector('input[name="region"]:checked')?.value, (result) => {
        let region = (typeof result === 'object' && result != null) ? result.data.region : result;
        let regionRadio = document.querySelector(`input[name="region"][value="${region ?? ''}"]`);
        regionRadio.checked = true;
        let label = document.querySelector(`label[for="${regionRadio.id}"]`);
        let text = document.querySelector('span[aria-label="region"]');
        text.innerHTML = label.textContent;
    }),
    new PageData('sex', () => document.querySelector('input[name="sex"]:checked')?.value, (result) => {
        let sex = (typeof result === 'object' && result != null) ? result.data.sex : result;
        let sexRadio = document.querySelector(`input[name="sex"][value="${sex ?? ''}"]`);
        sexRadio.checked = true;
        let label = document.querySelector(`label[for="${sexRadio.id}"]`);
        let text = document.querySelector('span[aria-label="sex"]');
        text.innerHTML = label.textContent;
    }),
    new PageData('state', () => document.querySelector('input[name="state"]:checked')?.value, (result) => {
        let state = (typeof result === 'object' && result != null) ? result.data.state : result;
        let stateRadio = document.querySelector(`input[name="state"][value="${state ?? ''}"]`);
        stateRadio.checked = true;
        let label = document.querySelector(`label[for="${stateRadio.id}"]`);
        let text = document.querySelector('span[aria-label="state"]');
        text.innerHTML = label.textContent;
    }),
    new PageData('startDate', () => document.querySelector('input[name="startDate"]')?.value, (result) => {
        let date = (typeof result === 'object' && result != null) ? dateFormat(result.data.startDate) : result;
        document.querySelector('input[name="startDate"]').value = date;
    }),
    new PageData('endDate', () => document.querySelector('input[name="endDate"]')?.value, (result) => {
        let date = (typeof result === 'object' && result != null) ? dateFormat(result.data.endDate) : result;
        document.querySelector('input[name="endDate"]').value = date;
    }),
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
    return '<a href="/admin/match/' + searchForm.matchId + '" class="result">' +
                '<span>' + jsonDateFormat(searchForm.matchDate) + '</span>' +
                '<span>' + searchForm.region + '</span>' + 
                '<span>' + searchForm.sex + '</span>' + 
                '<span>' + searchForm.title + '</span>' + 
                '<span>' + searchForm.person + '</span>' + 
                '<span>' + searchForm.matchStatus + '</span>' + 
            '</a>';
}
function dateFormat(dateStringType) {
    let date = new Date(dateStringType);
    return date.getFullYear() + '/' +
        String(date.getMonth() + 1).padStart(2, '0') + '/' +
        String(date.getDate()).padStart(2, '0');
}
function jsonDateFormat(json) {
    let date = new Date(json);
    return date.getFullYear() + '/' +
        String(date.getMonth() + 1).padStart(2, '0') + '/' +
        String(date.getDate()).padStart(2, '0') + ' ' +
        String(date.getHours()).padStart(2, '0') + ':' +
        String(date.getMinutes()).padStart(2, '0');
}
