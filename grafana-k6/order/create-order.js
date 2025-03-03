import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    vus: 10,
    duration: '30s',
};

export default function() {
    const url = 'http://localhost:8080/api/order';
    let num=Math.round(Math.random()*100000);

    let orderItems = []
    for (let i =0; i<10;i++){
        orderItems.push({
            productId: num,
            quantity: num
        })
    }
    const payload = JSON.stringify({
        userId: 'au1234' + num,
        email: `a${num}@gmail.com`,
        address: {
            country: 'America',
            state: 'New York',
            city: 'momo',
            street: 'central street',
            zipcode: '245154'
        },
        orderItems: [
            {

            },
            {
                productId: num,
                quantity: num
            },
            {
                productId: num,
                quantity: num
            }
        ],
        status: 'PAID',

    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };
    let res = http.post(url, payload, params);
    check(res, { "status is 200": (res) => res.status === 200 });
    sleep(1);
}
