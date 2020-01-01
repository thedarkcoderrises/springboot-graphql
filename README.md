# graphql-with-spring-boot
A sample application with GraphQL and Spring Boot

**SAMPLE**

```
**MUTATION**

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

**QUERY**

{
  person(name:"Abhi"){
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


{
  personOnAddress (input:{
    addLine1: "AL1",
    addLine2: "AL2",
    city:"Pune",
    state:"MH",
    country: NON_IND
  }){
     name
    age
    dob
    sex
    vehicle{
      type
      launchDate
      modelCode
      brandName
    }
  }
}

```