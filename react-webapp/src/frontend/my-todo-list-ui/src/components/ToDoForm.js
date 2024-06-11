import React from 'react'
import { Typography, Form, Input, Button } from 'antd'
const { Title } = Typography

function ToDoForm({ onFinish }) {
    return (
        <>
            <Title level={3}>Dodaj zadanie</Title>
            <Form
                name="layout-multiple-horizontal"
                autoComplete="off"
                onFinish={onFinish}
            >
                <Form.Item
                    label="Opis zadania"
                    name="description"
                    rules={[{ required: true, message: 'Prosze wprowadz opis zadania!' }]}
                >
                    <Input />
                </Form.Item>

                <Form.Item>
                    <Button
                        type="primary"
                        shape="round"
                        htmlType="submit"
                    >
                        Zapisz
                    </Button>
                </Form.Item>
            </Form>
        </>
    )
}

export default ToDoForm