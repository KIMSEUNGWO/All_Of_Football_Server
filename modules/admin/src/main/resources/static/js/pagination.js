import { fetchGet } from "./fetch.js";

export class Pagination {

    
    constructor(url, form) {
        this.url = url;
        this.getUrl = url + '/get';
        this.form = form;
        this.searchResultWrap = document.querySelector('#searchResult');
        const urlParams = new URLSearchParams(window.location.search);
        this.word = urlParams.get('word');
        this.region = urlParams.get('region');
        this.page = urlParams.get('page') ?? 1;


        this.pageList = document.querySelector('.page_list');
        this.firstBtn = document.querySelector('#first-page');
        this.preBtn = document.querySelector('#prev-page');
        this.nextBtn = document.querySelector('#next-page');
        this.lastBtn = document.querySelector('#last-page');

        this.initBtnEventListener();
    }

    searchBtn() {
        this.word = this.getWord();
        this.region = this.getRegion();
        this.page = 1;
        this.search();
    }
    search() {
        this.searchResultWrap.value = '';
        const request = this.getUrl + this.getParam();
        fetchGet(request, this.searchResult.bind(this));
    }


    searchResult(result) {
        const request = this.url + this.getParam();
        console.log(request);
        history.pushState({data : result}, '',  request);
        this.onPopState(result);
    }

    onPopState(result) {
        this.word = result.data.word;
        this.region = result.data.region;
        console.log(result);
        this.setCondition();

        const total = document.querySelector('.total');

        if (result.result !== 'OK') return;
        const pageable = result.data;

        this.pagination(pageable);

        if (pageable.content.length <= 0) {
            total.innerHTML = '총 0건';
            this.searchResultWrap.innerHTML = '<li class="none">검색 결과가 없습니다.</li>';
        } else {
            total.innerHTML = '총 ' + pageable.totalElements + '건';
            this.createFormList(pageable.content);
        }
    }

    createFormList(list) {
        let temp = ''
        for (let i=0;i<list.length;i++) {
            temp += this.form(list[i]);
        }
        this.searchResultWrap.innerHTML = temp;
    }

    pagination(pageable) {
        this.setBtn(pageable);
        this.setPage(pageable);
        this.pageEventListener();
    }

    setBtn(pageable) {
        this.totalPages = pageable.totalPages;

        this.firstBtn.disabled = (pageable.pageNumber < 1);
        this.preBtn.disabled = (pageable.pageNumber < 1);
        this.nextBtn.disabled = (pageable.pageNumber >= this.totalPages - 1);
        this.lastBtn.disabled = (pageable.pageNumber >= this.totalPages - 1);
    }
    setPage(pageable) {
        const count = 5;
        const currentPageNum = pageable.pageNumber;
        let pageCount = Math.floor(currentPageNum / 5);
        const startNum = Math.max(0, pageCount * count) + 1;
        this.createPageBtn(startNum, currentPageNum, count, pageable.totalPages);
    }

    createPageBtn(startNum, currentPageNum, count, lastPageNum) {
        this.pageList.innerHTML = '';
        for (let i=startNum;i < startNum+count && i <= lastPageNum;i++) {

            let li = document.createElement('li');
            li.classList.add('page');
            if (i === currentPageNum + 1) li.classList.add('selected');

            let btn = document.createElement('button');
            btn.type = 'button';
            btn.classList.add('page_link');
            btn.innerHTML = i;
            li.appendChild(btn);

            /*
                <li className="page selected">
                    <button type="button" class="page_link">1</button>
                </li>
            */

            this.pageList.appendChild(li);
        }
    }

    getParam() {
        let param = '?';
        let p = this.createParam('page', this.page);
        if (p != null) param += p;
        p = this.createParam('word', this.word);
        if (p != null) param += '&' + p;
        p = this.createParam('region', this.region);
        if (p != null) param += '&' + p;
        return param;
    }
    createParam(key, value) {
        if (value == null) return null;
        return `${key}=${value}`;
    }


    pageEventListener() {
        let pageBtn = document.querySelectorAll('.page_link');
        pageBtn.forEach(page => 
            page.addEventListener('click', () => {
                this.page = page.textContent;
                this.search();
            })
        )
    }
    setCondition() {
        let regionRadio = document.querySelector(`input[name="region"][value="${this.region ?? ''}"]`);
        regionRadio.checked = true;
        let label = document.querySelector(`label[for="${regionRadio.id}"]`);
        let text = document.querySelector('.option > .region.btnBox > span');
        text.innerHTML = label.textContent;

        let searchWord = document.querySelector('input[name="searchWord"]');
        searchWord.value = this.word ?? '';
    }

    setOption() {
        this.region = this.getRegion();
        this.word = this.getWord();
    }

    getRegion() {
        return document.querySelector('input[name="region"]:checked')?.value;
    }

    getWord() {
        return document.querySelector('input[name="searchWord"]')?.value;
    }

    initBtnEventListener() {
        window.addEventListener('load', () => {
            this.firstBtn.addEventListener('click', () => {
                this.page = 1;
                this.search();
            })
            this.preBtn.addEventListener('click', () => {
                this.page = Math.max(0, this.page - 1);
                this.search();
            })
            this.nextBtn.addEventListener('click', () => {
                this.page = Math.min(this.totalPages, this.page + 1);
                this.search();
            })
            this.lastBtn.addEventListener('click', () => {
                this.page = this.totalPages;
                this.search();
            })

            const searchWord = document.querySelector('input[name="searchWord"]');
            searchWord.addEventListener('keyup', (e)=>{
                if (searchWord.isEqualNode(e.target) && e.key === 'Enter') {
                    this.searchBtn();
                }
            })
            const searchBtn = document.querySelector('#searchBtn');
            searchBtn.addEventListener('click', ()=>{
                this.searchBtn();
            })
            const region = document.querySelector('.region');
            const regionOption = document.querySelector('.regionOption');

            region.addEventListener('click', () => regionOption.classList.toggle('disabled'));

            const inputRegion = document.querySelectorAll('input[name="region"]');
            inputRegion.forEach((el) => {
                el.addEventListener('change', (e) => {
                    regionOption.classList.add('disabled');

                    let check = document.querySelector('input[name="region"]:checked').id;
                    let label = document.querySelector('label[for="' + check +'"]')
                    let text = document.querySelector('.option > .region.btnBox > span');
                    text.innerHTML = label.textContent;
                })
            })
        })


    }
}