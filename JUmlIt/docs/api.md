### /account/register
* method: _POST_
* request:
```
{
	credentials: {
		email: '',
		password: '',
		fullname: ''
	}
}
```
* response:
```
{
	user {
		id: 0,
		email: '',
		fullname: ''
	}
}
```

### /account/login
* method: _POST_
* request:
```
{
	credentials: {
		email: '',
		password: ''
	}
}
```
* response:
```
{
	id: 0,
	email: '',
	fullname: ''
}
```

### /account/{id}
* method: _GET_
* response:
```
{
	id: 0,
	email: '',
	fullname: ''
}
```

### /account/{id}/details
* method: _GET_
* response:
```
{
	details: {
		personalInfo: '',
		ownDiagrams: [{
			name: 'Some name',
			id: 1
		}],
		collabDiagrams: [{
			name: 'Some name',
			id: 4,
			owner: {
				id: 1,
				fullname: 'Some fullname'
			}
		]
	}
}
```

### /account/{id}/details
* method: _POST_
* request:
```
{
	details: {
		personalInfo: ''
	}
}
```
* response:
```
{}
```

### /diagram/create
* method: _POST_
* request:
```
{
	diagram: {
		name: 'Some name',
		description: 'Some description',
		collaborators: [1, 3, 57] #ids of users
	}
}
```
* response:
```
{
	diagram: {
		id: 134
	}
}
```

### /diagram/{id}
* method: _GET_
* response:
```
{
	diagram: {
		id: 3,
		name: 'Some name',
		description: 'Some description',
		collaborators: [1, 3, 57] #ids of users,
		jsonData: ''
	}
}
```

### /diagram/{id}/update
* method: _POST_
* request:
```
{
	diagram: {
		id: 1,
		name: 'Some name',
		description: 'Some description',
		collaborators: [1, 3, 57] #ids of users,
		jsonData: ''
	}
}
```
* response:
```
{}
```

### /diagram/{id}/remove
* method: _POST_
* request:
```
{}
```
* response:
```
{}
```
