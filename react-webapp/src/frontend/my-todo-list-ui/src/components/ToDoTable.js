import React from 'react'
import { Typography, Button, Table, Tag } from 'antd'
import { CheckOutlined, DeleteOutlined } from '@ant-design/icons'
const { Title } = Typography

function ToDoTable({ todos, onComplete, onDelete }) {
    const columns = [
        {
            title: 'Opis zadania',
            dataIndex: 'description',
            key: 'description',
        },
        {
            title: 'Zakończone?',
            dataIndex: 'completed',
            key: 'completed',
            align: 'center',
        },
        {
            title: 'Zakończ',
            dataIndex: '',
            key: 'v',
            align: 'center',
            render: (text, record) => (
                <Button
                    icon={<CheckOutlined />}
                    type='primary'
                    shape="round"
                    onClick={(e) => { onComplete(record.key, e) }}
                >
                </Button>
            ),
        },
        {
            title: 'Usuń',
            dataIndex: '',
            key: 'x',
            align: 'center',
            render: (text, record) => (
                <Button
                    icon={<DeleteOutlined />}
                    type='primary'
                    shape="circle"
                    onClick={(e) => { onDelete(record.key, e) }}
                    danger
                >
                </Button>
            ),
        },
    ]

    const dataSource = todos.map(todo => {
        return {
            key: todo.id,
            description: todo.description,
            completed: todo.completed ?
                <Tag color="green">true</Tag> : <Tag color="red">false</Tag>
        }
    })

    return (
        <>
            <Title level={3}>Zadania do wykonania</Title>
            <Table
                dataSource={dataSource}
                columns={columns}
            />
        </>
    )
}

export default ToDoTable