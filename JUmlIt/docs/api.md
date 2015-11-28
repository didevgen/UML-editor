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
		classes: [] # collection of classes
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
		collaborators: [] # users,
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

### /diagram/{id}/classes/add
* method: _POST_
* request :
```
{
	name: '',
	position: {
		x: 213,
		y: 372
	}
}
```
* response:
```
{
	classId: 4,
	name: '',
	isStatic: false,
	accessModifier: 4,
	fields: [],
	methods: [],
	position: {
		x: 213,
		y: 372
	}
}

### /diagram/{id}/classes/update
* method: _POST_
* request :
```
{
	classId: 4,
	name: '',
	isStatic: false,
	accessModifier: 4,
	fields: [],
	methods: [],
	position: {
		x: 213,
		y: 372
	}
}
```
* response:
```
{}
```

### /diagram/{id}/classes/{id}/remove
* method: _POST_
* request :
```
{}
```
* response:
```
{}
```

### /diagram/{id}/classes/{id}
* method: _POST_
* request :
```
{}
```
* response:
```
{
	classId: 4,
	name: '',
	isStatic: false,
	accessModifier: 4,
	fields: [],
	methods: [],
	position: {
		x: 213,
		y: 372
	}
}
```


### /diagram/{id}/classes/{id}/fields/add
* method: _POST_
* request :
```
{
	accessModifier: 'public',
	isStatic: true,
	name: 'Some name',
	type: 'Some type'
}
```
* repsponse :
{
	id: 4,
	accessModifier: 'public',
	isStatic: true,
	name: 'Some name',
	type: 'Some type'
}

### /diagram/{id}/classes/{id}/methods/add
* method: _POST_
* request :
```
{
	accessModifier: 'public',
	isStatic: true,
	name: 'Some name',
	returnType: 'Some type',
	arguments: [{
		name: 'arg1',
		type: 'String'
	}]
}
```
* repsponse :
{
	id: 4,
	accessModifier: 'public',
	isStatic: true,
	name: 'Some name',
	returnType: 'Some type',
	arguments: [{
		name: 'arg1',
		type: 'String'
	}]
}


### /diagram/{id}/classes/{id}/fields/{id}/remove
* method: _POST_
* request :
```
{}
```
* response:
```
{}
```

### /diagram/{id}/classes/{id}/methods/{id}/remove
* method: _POST_
* request :
```
{}
```
* response:
```
{}
```

### /diagram/{id}/classes/{id}/methods/update
* method: _POST_
* request :
```
{
	id: 4,
	accessModifier: 'public',
	isStatic: true,
	name: 'Some name',
	returnType: 'Some type',
	arguments: [{
		name: 'arg1',
		type: 'String'
	}]
}
```
* repsponse :
```
{}
```

### /diagram/{id}/classes/{id}/fields/update
* method: _POST_
* request :
```
{
	id: 4,
	accessModifier: 'public',
	isStatic: true,
	name: 'Some name',
	type: 'Some type'
}
```
* response:
```
{}
```
