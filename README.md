# graphql-with-spring-boot
A sample application with GraphQL and Spring Boot

**SAMPLE**


**MUTATION**
```
mutation{
  createPerson(input:{
    name:"Abhi6",
    sex:"M",
    dob: "1988-09-05",
    age: 30,
    addLine1: "AL1",
    addLine2: "AL2",
    city:"Pune",
    state:"MH",
    country: NON_IND,
    type: CAR,
    engineType: FOUR_STROKE,
    modelCode: "Venue",
    brandName: "Hyundai",
    launchDate: "2019-08-08"
  }) {
    name
    age
    dob
    sex
  }
}
```
**QUERY**
```
{
  persons(name:"Abhi"){
    name,
    age,
    sex,
    address{
      addLine1
      addLine2
      state
      city
      country
    }
    vehicle{
      type
      launchDate
      modelCode
      brandName
      formattedDate
    }
  }
}

```

**NESTED-QUERY**
```
{
  persons{
    uid
    name
    age
    sex
    dob
    address {
      addressId
      addLine1
      addLine2
      city
      state
      country
    }
    vehicle {
      vehicleId
      type
      modelCode
      brandName
      launchDate
      formattedDate
      engineType
    }
  }
}
```

**NESTED-QUERY-FRIEND**
```
{
  persons{
    name
    friends {
      idiots {
        name
      }
    }
  }
}
```

**FRAGMENT & ALIAS**
```
query PersonComparison($auid: Int ,$muid: Int) {
	abhi: person(uid: $auid) {
    ...comparisonFields
  }
  manth: person(uid: $muid) {
    ...comparisonFields
  }
}

fragment comparisonFields on Person {
 name
   friends{
    idiots{
      name
      age
    }
  }
}

QUERY VARIABLES
{
  "auid":1,
  "muid":3
}

```

**DIRECTIVE**

```
query PersonComparison($auid: Int ,$muid: Int, $friends: Boolean!) {
	abhi: person(uid: $auid) {
    ...comparisonFields
  }
  manth: person(uid: $muid) {
    ...comparisonFields
  }
}


fragment comparisonFields on Person {
 name
  address {
    addLine1
    addLine2
    city
    state
    country
  }
  vehicle {
    type
    modelCode
    brandName
    launchDate
    formattedDate
    engineType
  }
   friends  @include(if: $friends){
    idiots{
      name
      address {
        addLine1
        addLine2
        city
        state
        country
      }
      vehicle {
        type
        modelCode
        brandName
        launchDate
        formattedDate
        engineType
      }
    }
  }
}


QUERY VARIABLES

{
  "auid": 1,
  "muid": 3,
  "friends": false
}
```