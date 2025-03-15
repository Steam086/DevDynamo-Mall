// 对照测试，检验只有一个return String的controller性能
import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    vus: 1000,
    duration: '30s',
};
const params = {
    headers: {
        'Content-Type': 'application/json',
    },
};

const url = 'http://localhost:8080/empty';

export default function() {

    let res = http.get(url, params);
    check(res, { "status is 200": (res) => res.status === 200 });
    sleep(1);
}
// K6_PROMETHEUS_RW_SERVER_URL=http://localhost:9090/api/v1/write \
// k6 run -o experimental-prometheus-rw empty.js
