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
		classes: [] # collection of classes,
		relationships: [{ # collection of relationships
			"id": 0,
			"type": "aggregation",
			"primaryMemberId": 3,
			"secondaryMemberId": 4,
			"primaryToSecondaryMultiplicity": "0..1",
			"secondaryToPrimaryMultiplicity": "1"
		}]
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
		collaborators: [] # users,,
		classes: [],
		relationships: []
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
```

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
```
{
	id: 4,
	accessModifier: 'public',
	isStatic: true,
	name: 'Some name',
	type: 'Some type'
}
```
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

### /diagram/{id}/relationships/add
* method: _POST_
* request :
```
{
	"type": "aggregation",
	"primaryMemberId": 3,
	"secondaryMemberId": 4,
	"primaryMultiplicity": "0..1",
	"secondaryMultiplicity": "1"
	"name": "some name",
	"primaryProps": "text"
	"secondaryProps": "text"
}
```
* response:
```
{
	"type": "aggregation",
	"primaryMemberId": 3,
	"secondaryMemberId": 4,
	"primaryMultiplicity": "0..1",
	"secondaryMultiplicity": "1"
	"name": "some name",
	"primaryProps": "text"
	"secondaryProps": "text",
	
	# server adds this before returning
	"id": 4,
	"primaryMember": {} # user obj
	"secondaryMember": {} # user obj
}
```

### /diagram/{id}/relationships/update
* method: _POST_
* request :
```
{
	"type": "aggregation",
	"primaryMemberId": 3,
	"secondaryMemberId": 4,
	"primaryMultiplicity": "0..1",
	"secondaryMultiplicity": "1"
	"name": "some name",
	"primaryProps": "text"
	"secondaryProps": "text",
	"id": 4,
	"primaryMember": {} # user obj
	"secondaryMember": {} # user obj
}
```
* response:
```
{}
```

### /diagram/{id}/relationships/{id}/remove
* method: _POST_
* request :
```
{}
```
* response
```
{}
```
### /diagram/{id}/relationships/{id}
* method: _GET_
* request :
```
{}
```
* response:
```
{
	"type": "aggregation",
	"primaryMemberId": 3,
	"secondaryMemberId": 4,
	"primaryMultiplicity": "0..1",
	"secondaryMultiplicity": "1"
	"name": "some name",
	"primaryProps": "text"
	"secondaryProps": "text",
	"id": 4,
	"primaryMember": {} # user obj
	"secondaryMember": {} # user obj
}
```

--------------------------------
## History of changes


### /diagram/{diagramId}/history
* method: _GET_
* request :
```
{}
```
* response:
```
{
	"diagramId": 3,
	"sessions": [{
		"sessionId": 5,
		"userId": 3,
		"userName": "Max Selekh",
		"time-start": "2015-12-12 15:23:23",
		"time-end": "2015-12-12 15:23:23", # null if session is not finished
	}]
}
```

### /diagram/{disgramId}/history/{sessionId}
* method: _GET_
* request :
```
{}
```
* response:
```
{
	"diagramId": 3,
	"session": {
		"sessionId": 4,
		"userId": 3,
		"userName": "Max Selekh"
		"time_start": "2015-12-12 15:23:23",
		"time_end": "2015-12-12 15:23:23", # null, if session isn't finished
		"changes": [{
			"actionId": 2342,
			"time": "2015-12-12 15:23:22",
			"details": {
				"action": "added", # changed/renamed/added/removed or other
				"object": "class": # type of object: link, class, method or variable,
				"name": "Clazz" # name of object
			}
		}]
	}
}
```
