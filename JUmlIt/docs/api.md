### /account/register
* method: _POST_
* request:
```
{
	email: '',
	password: '',
	fullname: ''
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

### /account/login
* method: _POST_
* request:
```
{
	email: '',
	password: ''
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
	personalInfo: ''
}
```

### /account/{id}/details
* method: _POST_
* request:
```
{
	personalInfo: ''
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
	name: 'Some name',
	description: 'Some description',
	collaborators: [1, 3, 57] #ids of users
}
```
* response:
```
{
	id: 134
}
```

### /diagram/{id}
* method: _GET_
* response:
```
{
	name: 'Some name',
	description: 'Some description',
	collaborators: [1, 3, 57] #ids of users,
	jsonData: ''
}
```

### /diagram/{id}/update
* method: _POST_
* request:
```
{
	name: 'Some name',
	description: 'Some description',
	collaborators: [1, 3, 57] #ids of users,
	jsonData: ''
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
