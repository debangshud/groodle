var configuration = {
    _id: 'dev',
    members: [
        {_id:0, host: '192.168.86.35:27017'},
        {_id:1, host: '192.168.86.29:27017', slaveDelay: 20, priority: 0},
        {_id:2, host: '192.168.86.37:27017'}
    ]
}