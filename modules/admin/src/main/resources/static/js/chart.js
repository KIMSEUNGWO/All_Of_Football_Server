let regionData = null;
let regionChart = null;
let regionSort = 'COMPLETE_ASC';

const regionSortEnum = {
    'COMPLETE_ASC': (o1, o2) => o1.completedCount - o2.completedCount,
    'COMPLETE_DESC': (o1, o2) => o2.completedCount - o1.completedCount,
    'CANCEL_ASC': (o1, o2) => o1.canceledCount - o2.canceledCount,
    'CANCEL_DESC': (o1, o2) => o2.canceledCount - o1.canceledCount
};

window.addEventListener('load', () => {

    const regionSorts = document.querySelector('select[name="region-sort"]');

    regionSorts.addEventListener('change', (e) => {
        regionSort = e.target.value;
        barChartDraw();
    })

    const statCards = document.querySelectorAll('.stat-card');
    statCards.forEach(statCard => {
        let color = statCard.getAttribute('aria-border-color');
        statCard.style.borderLeft = `${color} solid 10px`;
    })

    const chartCards = document.querySelectorAll('.chart-card');
    chartCards.forEach(chartCard => {
        let label = chartCard.getAttribute('aria-label');
        let datas = chartCard.querySelectorAll('.data');
        let chartData = new ChartData(datas);

        let labelElement = createLabelElement(label);
        chartCard.appendChild(labelElement);

        const canva = chartCard.querySelector('canvas');

        new Chart(canva, {
            type: 'doughnut',
            data: {
                labels: chartData.labels,
                datasets: [{
                    data: chartData.dataSets,
                    backgroundColor: chartData.colors
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    },
                    title: {
                        display: false,
                    }
                }
            }
        });

        datas.forEach(data => data.remove());
    })



})

function createLabelElement(label) {
    let div = document.createElement('div');
    div.classList.add('chart-title');
    div.innerHTML = label;
    return div;
}

function calButtonClick(startDate, endDate) {
    fetch(`/admin/statistics/region?startDate=${startDate}&endDate=${endDate}`)
    .then(res => res.json())
    .then(json => {
        if (json.result !== 'OK') return;

        regionData = new ChartBarData(json.data);
        barChartDraw();

    });
}

function barChartDraw() {
    // 기존 차트가 있다면 제거
    if (regionChart !== null) {
        regionChart.destroy();
    }

    let sortedData = regionData.datas.sort(regionSortEnum[regionSort]);
    console.log(sortedData);
    regionChart = new Chart(document.querySelector('#regionChart'), {
        type: 'bar',
        data: {
            labels: sortedData.map(data => data.region),
            datasets: [
                {
                    label: '완료',
                    data: sortedData.map(data => data.completedCount),
                    backgroundColor: '#3F9DE0',
                    borderRadius: {
                        topLeft: 0,     topRight: 0,
                        bottomLeft: 3,  bottomRight: 3
                    },
                    borderSkipped: false,
                    barPercentage: 0.8
                },
                {
                    label: '취소',
                    data: sortedData.map(data => data.canceledCount),
                    backgroundColor: '#e3e6f0',
                    borderRadius: {
                        topLeft: 3,     topRight: 3,
                        bottomLeft: 0,  bottomRight: 0
                    },
                    borderSkipped: false,
                    barPercentage: 0.8
                }
            ].sort((o1, o2) => {
                if (regionSort === 'CANCEL_ASC' || regionSort === 'CANCEL_DESC') {
                    o2.borderRadius = {
                        topLeft: 3,     topRight: 3,
                        bottomLeft: 0,  bottomRight: 0
                    };
                    o1.borderRadius = {
                        topLeft: 0,     topRight: 0,
                        bottomLeft: 3,  bottomRight: 3
                    };
                    return -1;
                }
            })
        },
        options: {
            responsive: true,
            scales: {
                x: {
                    stacked: true,
                    grid: {
                        display: false
                    }
                },
                y: {
                    stacked: true,
                    beginAtZero: true,
                    grid: {
                        color: '#e3e6f0'
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                }
            }
        }
    });
}

class ChartData {

    constructor(datas) {
        let labels = [];
        let dataSets = [];
        let colors = [];
        datas.forEach(data => {
            labels.push(data.getAttribute('data-is-label'));
            dataSets.push(Number(data.getAttribute('data-is')));
            colors.push(data.getAttribute('data-is-color'));
        })

        this.labels = labels;
        this.dataSets = dataSets;
        this.colors = colors;
    }
}

class ChartBarData {

    constructor(datas) {
        console.log('ChartBarData : ', datas);
        let regionDatas = [];
        datas.forEach(data => regionDatas.push(new ChartRegionData(data)));
        this.datas = regionDatas;
    }
}

class ChartRegionData {

    constructor(data) {
        this.region = data.region;
        this.completedCount = data.completedCount;
        this.canceledCount = data.canceledCount;
    }
}