import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    vus: 10,
    duration: '30s',
};

// 动态生成订单数据
function generateOrderData() {
    const userId = Math.floor(Math.random() * 1000); // 随机生成用户ID
    const addressId = Math.floor(Math.random() * 100); // 随机生成地址ID
    const orderItemsCount = Math.floor(Math.random() * 5) + 1; // 随机生成订单项数量

    const orderItems = [];
    for (let i = 0; i < orderItemsCount; i++) {
        orderItems.push({
            productId: Math.floor(Math.random() * 100), // 随机生成产品ID
            quantity: Math.floor(Math.random() * 10) + 1, // 随机生成数量
        });
    }

    return {
        userId: userId,
        address: {
            id: addressId,
            // 其他地址相关字段
        },
        orderItems: orderItems,
        status: 'PENDING', // 假设有一个 PENDING 状态
    };
}
const BASE_URL = 'http://localhost/api/order';
const params = {
    headers: {
        'Content-Type': 'application/json',
    },
}
export default function () {
    const orderData = generateOrderData(); // 生成动态订单数据
    let payload = JSON.stringify(orderData)

    const response = http.post(BASE_URL, payload, params);
    check(res, { "status is 200": (res) => res.status === 200 });
    sleep(1);
}
