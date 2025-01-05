# Syntax

#### **1. Variable Declarations and Typing**

Lum has explicit typing, which is used with `:=` operator.
This operator may be used when you're changing the type of variable.
When you don't want to change the type of variable, you may use `=` operator.
<br/>
<br/>
Lum has union types, which can be thought of as a set of available types for variables. 
For example, if you have a variable `a` with the type `A | B` (which means it can be `A` or `B`),
and you want to assign the value of `B` to it, use the `=` operator. 
However, if you try to assign a returned value from some function `f()`, with a return type of `B | C`,
you should use `:=` operator.

* **Examples of Automatic Type Coercion**:

    ```lum
    a := 42 
    b := 'Hello, World!' 
    c := 3.14 
    print(a, b, c)
    ```

* **Explicit Typing and Union Types**:
    ```lum
    x: int = 10 
    y: str | None = None 
    y = "Now I'm a string" 
    print(x, y)
    ```
* * *

#### **2. Functions and Methods**

* **Simple Function Definition**:

    ```lum
    func sayHello(name: str) { 
        print(f'Hello, {name}!') 
    } 
    sayHello('Alice')
    ```

* **Function with a Return Value**:

    ```lum
    func add(a: int, b: int): int { 
        return a + b 
    } 
    print(add(5, 7))
    ```

* **Function Without a Return Type**:

    ```lum
    func log(message: str) { 
        print(f'LOG: {message}') 
    } 
    log('This is a log message.')
    ```

* * *

#### **3. Classes and Inheritance**

* **Simple Class Declaration**:

    ```lum
    class Animal { 
        name: str 
        init(name: str) { 
            this.name = name 
        } 
        func speak(): str { 
            return "..." 
        } 
    }
    ```

* **Inheritance and Interfaces**:

    ```lum
    interface Pet { 
        func play() 
    } 
    class Dog(extends=Animal, implements=[Pet]) { 
        public override func speak(): str { 
            return "Woof!" 
        } 
        public func play() { 
            print("The dog is playing!") 
        } 
    } 
    rover := Dog('Rover') 
    print(rover.speak()) 
    rover.play()
    ```

* * *

#### **4. Loops and Conditions**

* **Single-Line Conditions**:

    ```lum
    if x > 0: print("Positive")
    ```

* **Multi-Line Conditions**:

    ```lum
    if x > 0 { 
        print("Positive") 
        print(f"Value is {x}") 
    } else { 
        print("Non-positive") 
    }
    ```

* **Loops**:

    ```lum
    for i in 0..10: 
        print(i) 
    for item in ['apple', 'banana', 'cherry'] { 
        print(item) 
    } 
    while x > 0 { 
        print(x) 
        x-- 
    }
    ```

* * *

#### **5. Generics**

* **Using Generics in Classes**:

    ```lum
    class Box[T] { 
        value: T 
        init(value: T) { 
            this.value = value 
        } 
  
        func get(): T { 
            return this.value 
        } 
    } 
    intBox := Box(intBox.get())
    ```

* **Type Constraints**:

    ```lum
    func [T: Number] doubleValue(value: T): T { 
        return value * 2 
    } 
    print(doubleValue(5))
    ```

* * *

#### **6. Annotations and Decorators**

* **Defining an Annotation**:

    ```lum
    annotation Deprecated(message: str) {} 
    @Deprecated(message="Use 'newFunc' instead") 
    func oldFunc() { 
        print("This is deprecated") 
    }
    ```

* **Applying a Decorator**:

    ```lum
    annotation logExecution { 
        print(f'Executing {func()}') 
    } 
    @logExecution 
    func greet(name: str) { 
        print(f'Hello, {name}') 
    } 
    greet("Alice")
    ```

* * *

#### **7. Collections**

* **Lists**:

    ```lum
    numbers := [1, 2, 3, 4, 5] 
    for n in numbers: 
        print(n)
    ```

* **Dictionaries**:

    ```lum
    user := {"name": "Alice", "age": 25} 
    print(user["name"])
    ```

* * *

#### **8. Error Handling**

    ```lum
    try { 
        riskyOperation() 
    } except IOError as e { 
        print(f"Error occurred: {e}") 
    } finally { 
        print("Cleanup done") 
    }
    ```

* * *

#### **9. Lambdas and Functional Interfaces**

* **Simple Lambdas**:

    ```lum
    func applyToList(list: List[int], operation: [int] => int) { 
        for i := 0, i < list.size(), i++: 
            list[i] := operation(list[i]) 
    } 
    nums := [1, 2, 3, 4] 
    applyToList(nums, x => x * 2) 
    print(nums)
    ```

* **Passing Functions as Arguments**:

    ```lum
    func printMessage(func: [str] => void, message: str) { 
        func(message) 
    } 
    printMessage(msg => print(msg), "Hello from a lambda!")
    ```

* * *

#### **10. Syntactic Sugar**

* **Single-Line Constructs**:

    ```lum
    if x > 0: print("Positive") 
    for item in items: print(item)
    ```

* **Multi-Line Constructs**:

    ```lum
    for item in items { 
        if item > 10 { 
            print("Big item") 
        } 
    }
    ```

* * *

#### **11. Switch-Case Statement**

* **Single-Line Cases**:

    ```lum
    switch x { 
        case 1: print("One") 
        case 2: print("Two") 
    }
    ```

* **Multi-Line Cases**:

    ```lum
    switch x { 
        case 1 { 
            print("It's one") 
            log("One handled") 
        } 
        default { 
            print("Unknown value") 
        } 
    }
    ```
