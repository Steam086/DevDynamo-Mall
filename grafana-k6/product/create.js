import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    vus: 200,
    duration: '50s',
};

const params = {
    headers: {
        'Content-Type': 'application/json',
    },
};

const url = 'http://localhost:8080/empty/create';

export default function() {

    let num=Math.round(Math.random()*10000);

    const payload = JSON.stringify({
        name: 'au1234' + num,
        description: `a${num}31311wodah你好22`,
        picture: 'bbb1234rs5',
        price: 11.1+num,
        categories: [
            {name: "kind1"},
            {name: "kind2"},
            {name: "kind3"}
        ]
    });
    let res = http.post(url, payload, params);
    check(res, { "status is 200": (res) => res.status === 200 });
    sleep(1);
}
