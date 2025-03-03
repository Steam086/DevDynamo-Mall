import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    vus: 10,
    duration: '30s',
};

export default function() {
    const url = 'http://localhost:8080/api/auth/';
    let num=Math.round(Math.random()*100000);

    // const payload = JSON.stringify({
    //     username: 'au1234' + num,
    //     email: `a${num}@gmail.com`,
    //     password: 'bbb1234rs5',
    //     firstname: 'Donald',
    //     lastname: `Trump${num}`,
    // });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };
    let res = http.post(url, payload, params);
    check(res, { "status is 200": (res) => res.status === 200 });
    sleep(1);
}
