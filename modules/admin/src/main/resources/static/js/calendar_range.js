const date = new Date();

const fixYear = date.getFullYear();
const fixMonth = date.getMonth() + 1;
const fixDay = date.getDate();

let year = date.getFullYear();
let month = date.getMonth() + 1;
let day = date.getDate();

let flag = true; //  true = 왼쪽 startDate 변경됨, false = 오른쪽 endDate 변경됨

window.addEventListener('load', () => {

    const preBtn = document.querySelector('#preButton');
    const nextBtn = document.querySelector('#nextButton');

    const startDate = document.querySelector('input[name="startDate"]');
    const endDate = document.querySelector('input[name="endDate"]');
    const calendar = document.querySelector('#date_range_calendar');

    initial();

    let confirmBtn = document.querySelector('#calendar_confirm');
    confirmBtn.addEventListener('click', () => {
        calendar.classList.add('disabled');
    })

    startDate.addEventListener('click', () => {
        calendar.classList.toggle('disabled');
        flag = true;
        focusInputDate(startDate, endDate);
    })
    endDate.addEventListener('click', () => {
        calendar.classList.toggle('disabled');
        flag = false;
        focusInputDate(startDate, endDate);
    })


    let renderCalendar = () => {
        let rightMonth = document.querySelector('#rightMonth');
        let rightBody = document.querySelector('#rightBody');
        lender(rightMonth, rightBody, year, month);
    
        let leftMonth = document.querySelector('#leftMonth');
        let leftBody = document.querySelector('#leftBody');
        lender(leftMonth, leftBody, year, month-1);

        // 이번달 기준 2달 후까지 표시
        let calDate = new Date(year, month-1, 0);
        let compareDate = new Date(fixYear, fixMonth+1, 0);

        nextBtn.disabled = calDate.getTime() >= compareDate.getTime();

        checkToday();
        drawRange();
    
    };

    renderCalendar();

    preBtn.addEventListener('click', () => {
        if (month === 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        renderCalendar();
    })
    nextBtn.addEventListener('click', () => {
        if (month-1 === 12) {
            month = 1;
            year++;
        } else {
            month++;
        }

        renderCalendar();
    })


    document.addEventListener('click', (e) => {
        if (e.target.hasAttribute('aria-pressed') && e.target.hasAttribute('data-is-today')) {

            let clickDate = clickAndGetDate(e.target);

            let startDateForm = getDateForm(startDate);
            let endDateForm = getDateForm(endDate);

            let innerDate = dateForm(clickDate.getFullYear(), clickDate.getMonth()+1, clickDate.getDate());

            selectDateForm(innerDate, clickDate.getTime(), startDate, startDateForm.getTime(), endDate, endDateForm.getTime());
            focusInputDate(startDate, endDate);
            drawRange();
        }
    })

})

function initial() {
    let startDate = document.querySelector('input[name="startDate"]');
    let endDate = document.querySelector('input[name="endDate"]');
    if (startDate.value !== '' && endDate.value !== '') return;

    const addDay = 30;

    let date = new Date();

    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    startDate.value = dateForm(year, month, day);

    let newDate = new Date(year, month-1, day+addDay);
    console.log(newDate);
    let newYear = newDate.getFullYear();
    let newMonth = newDate.getMonth() + 1;
    let newDay = newDate.getDate();

    endDate.value = dateForm(newYear, newMonth, newDay);
}

function focusInputDate(start, end) {
    if (flag) {
        start.focus();
    }
    if (!flag) {
        end.focus();
    }
}

function selectDateForm(innerDate, clickDate, start, startDateForm, end, endDateForm) {
    if (clickDate < startDateForm) {
        start.value = innerDate;
        flag = false;
        return;
    }
    if (clickDate > startDateForm && clickDate < endDateForm) {
        if (flag) {
            start.value = innerDate;
            flag = !flag;
        } else {
            end.value = innerDate;
            flag = !flag;
        }
        return;
    }
    if (clickDate > endDateForm) {
        if (flag) {
            start.value = innerDate;
            end.value = innerDate;
            flag = !flag;
        } else {
            end.value = innerDate;
            flag = !flag;
        }
        return;
    }
}

function clickAndGetDate(target) {
    let parentId = target.parentElement.parentElement.parentElement.id;

    let withinDay = Number(target.textContent);
    
    if (parentId == 'leftBody') {
        let lMonth = document.querySelector('#leftMonth').textContent;
        let lArray = lMonth.split(". ");
        return new Date(Number(lArray[0]), Number(lArray[1])-1, withinDay);
    }
    else if (parentId == 'rightBody'){
        let rMonth = document.querySelector('#rightMonth').textContent;
        let rArray = rMonth.split(". ");
        return new Date(Number(rArray[0]), Number(rArray[1])-1, withinDay);
    }
}

function drawRange() {
    clearRange();
    let startDate = document.querySelector('input[name="startDate"]');
    let endDate = document.querySelector('input[name="endDate"]');

    let start = getDate(startDate.value);
    let end = getDate(endDate.value);

    if (start == null || end == null) {
        return;
    }
    let dateStart = new Date(Number(start[0]), Number(start[1])-1, Number(start[2]));
    let dateEnd = new Date(Number(end[0]), Number(end[1])-1, Number(end[2]));
    drawLeftRange(dateStart, dateEnd);
    drawRigtRange(dateStart, dateEnd);

    // 왼쪽 끝 오른쪽끝 색 변경
    let within = document.querySelectorAll(".cell[data-is-within-range='true']");
    if (within.length != 0) {
        checkStartDay(dateStart, within[0]);
        checkLastDay(dateEnd, within[within.length-1]);
    }
}
function clearRange() {
    let all = document.querySelectorAll('.cell[data-is-within-range="true"]');
    all.forEach(el => {
        el.setAttribute('data-is-within-range', 'false');
        el.style = 'initial';
    })
    let allBtn = document.querySelectorAll('button[aria-pressed="true"]');
    allBtn.forEach(el => {
        el.setAttribute('aria-pressed', 'false');
    })
    
}

function checkStartDay(dateStart, within) {
    let parentId = within.parentElement.parentElement.id;
    let withinDay = Number(within.children.item(0).textContent);
    if (parentId == 'leftBody') {
        let lMonth = document.querySelector('#leftMonth').textContent;
        let lArray = lMonth.split(". ");
        let ldate = new Date(Number(lArray[0]), Number(lArray[1])-1, withinDay);

        if (dateStart.getTime() != ldate.getTime()) {
            return;
        }
    }
    else if (parentId == 'rightBody'){
        let rMonth = document.querySelector('#rightMonth').textContent;
        let rArray = rMonth.split(". ");
        let rdate = new Date(Number(rArray[0]), Number(rArray[1])-1, withinDay);
        if (dateStart.getTime() != rdate.getTime()) {
            return;
        }
    }
    within.style.backgroundColor = '#ffffff00'
    within.children.item(0).setAttribute('aria-pressed', 'true');
}

function checkLastDay(dateEnd, within) {
    let parentId = within.parentElement.parentElement.id;
    let withinDay = Number(within.children.item(0).textContent);
    if (parentId == 'leftBody') {
        let lMonth = document.querySelector('#leftMonth').textContent;
        let lArray = lMonth.split(". ");
        let ldate = new Date(Number(lArray[0]), Number(lArray[1])-1, withinDay);

        if (dateEnd.getTime() != ldate.getTime()) {
            return;
        }
    }
    else if (parentId == 'rightBody'){
        let rMonth = document.querySelector('#rightMonth').textContent;
        let rArray = rMonth.split(". ");
        let rdate = new Date(Number(rArray[0]), Number(rArray[1])-1, withinDay);
        
        if (dateEnd.getTime() != rdate.getTime()) {
            return;
        }
    }

    within.children.item(0).setAttribute('aria-pressed', 'true');
}

function drawLeftRange(start, end) {
    let leftMonth = document.querySelector('#leftMonth');
    let left = leftMonth.textContent.split(". ");
    let cell = document.querySelectorAll('#leftBody .cell[data-is-within-range="false"], #leftBody .cell[data-is-within-range="true"]');
    for (let i=0;i<cell.length;i++) {
        let temp = new Date(Number(left[0]), Number(left[1]) - 1, i + 1);
        if (start <= temp && end >= temp) {
            cell[i].setAttribute('data-is-within-range', 'true');
        }
    }
}

function drawRigtRange(start, end) {
    let rightMonth = document.querySelector('#rightMonth');
    let right = rightMonth.textContent.split(". ");
    let cell = document.querySelectorAll('#rightBody .cell[data-is-within-range="false"], #rightBody .cell[data-is-within-range="true"]');
    for (let i=0;i<cell.length;i++) {
        let temp = new Date(Number(right[0]), Number(right[1]) - 1, i + 1);
        if (start <= temp && end >= temp) {
            cell[i].setAttribute('data-is-within-range', 'true');
        }
    }

}

function checkToday() {
    let leftMonth = document.querySelector('#leftMonth');
    let rightMonth = document.querySelector('#rightMonth');
    let left = leftMonth.textContent.split(". ");
    let right = rightMonth.textContent.split(". ");
    if (fixYear == Number(left[0]) && fixMonth == Number(left[1])) {
        drawToday(document.querySelector('#leftBody'));
    }
    if (fixYear == Number(right[0]) && fixMonth == Number(right[1])) {
        drawToday(document.querySelector('#rightBody'));
    }
}
function drawToday(body) {
    let selector = '#' + body.id + ' button[data-is-today="false"]';
    let cellList = document.querySelectorAll(selector);
    cellList[fixDay-1].setAttribute('data-is-today', 'true');
}

function lender(header, body, year, month) {
    // 2023. 11 형식
    if (month == 0) {
        year--;
        month = 12;
    }
    if (month == 13) {
        year++;
        month = 1;
    }
    header.innerHTML = year + ". " + String(month).padStart(2, '0');

    // 현재월의 마지막날
    let lastDateMonth = new Date(year, month, 0).getDate();
    // 현재달 시작요일
    let startDateMonth = new Date(year, month-1, 1).getDay();
    

    let temp = '<div class="week"><div class="cell"><div>일</div></div><div class="cell"><div>월</div></div><div class="cell"><div>화</div></div><div class="cell"><div>수</div></div><div class="cell"><div>목</div></div><div class="cell"><div>금</div></div><div class="cell"><div>토</div></div></div></div>';
    let now = 1;

    while (now <= lastDateMonth) {
        temp +=  '<div class="week">' + createWeek(now, lastDateMonth, startDateMonth) + '</div>';
        now += 7 - startDateMonth;
        startDateMonth = 0;
    }
    
    body.innerHTML = temp;
}


function createWeek(now, lastDateMonth, startDateMonth) {
    let temp = '';
    for (let i=startDateMonth;i<7;i++) {
        if (now <= lastDateMonth) {
            temp += '<div class="cell" data-is-within-range="false"><button type="button" aria-pressed="false" data-is-today="false">' + now + '</button></div>'
            now++;
        }
    }
    return temp;
}


function getDate(value) {
    if (value === '') {
        return null;
    }
    let split = value.split("/");
    let array = [];
    array.push(split[0]);
    array.push(split[1]);
    array.push(split[2]);
    return array;
}
function dateForm(year, month, day) {
    return year + '/' + String(month).padStart(2, '0') + '/' + String(day).padStart(2, '0');
}
function getDateForm(date) {
    let split = date.value.split('/');
    return new Date(Number(split[0]), Number(split[1])-1, Number(split[2]));
}