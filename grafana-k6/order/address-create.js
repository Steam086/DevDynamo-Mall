import http from 'k6/http';
import { check, sleep } from 'k6';
import { randomIntBetween, randomString } from 'https://jslib.k6.io/k6-utils/1.0.0/index.js';

export let options = {
    stages: [
        { duration: '2m', target: 100 }, // 模拟用户数量逐渐增加到100，持续2分钟
        { duration: '5m', target: 100 }, // 保持100用户负载，持续5分钟
        { duration: '2m', target: 0 },   // 用户数量逐渐减少到0，持续2分钟
    ],
};

function generateRandomAddress() {
    const countries = ['China', 'USA', 'Canada', 'Australia', 'Germany', 'France', 'Japan'];
    const states = ['Beijing', 'California', 'Ontario', 'New South Wales', 'Bavaria', 'Île-de-France', 'Tokyo'];
    const cities = ['Beijing', 'San Francisco', 'Toronto', 'Sydney', 'Munich', 'Paris', 'Tokyo'];
    const streets = ['No.1 Street', 'Main St', 'Queen St', 'King St', 'Broadway', 'Champs-Élysées', 'Ginza'];
    const zipcodes = [100000, 94105, M5J2G8, 2000, 80001, 75008, 104000];

    return {
        country: countries[randomIntBetween(0, countries.length - 1)],
        state: states[randomIntBetween(0, states.length - 1)],
        city: cities[randomIntBetween(0, cities.length - 1)],
        street: streets[randomIntBetween(0, streets.length - 1)],
        zipcode: zipcodes[randomIntBetween(0, zipcodes.length - 1)],
    };
}

export default function () {
    const url = 'http://localhost/api/addresses'; // 替换为你的API接口地址
    const address = generateRandomAddress();
    const payload = JSON.stringify(address);
    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'is status 201': (r) => r.status === 201, // 假设创建地址的接口返回状态码201表示成功
    });

    sleep(1); // 每次请求间隔1秒
}