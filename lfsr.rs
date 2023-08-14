extern crate rand;

use self::rand::Rng;


/// A Linear-Feedback-Shift-Register.
///
/// Start with a random Initialization Vector.
/// Then 1st bit will be taken as output.
/// XOR with 3rd and 7th bit.
/// The result will be used as a new input and the register is shifted 1 bit to the left
///
/// Specify the size of the register with buffer_size
pub fn lfsr(buffer_size: i32){
    if buffer_size < 7 {
        panic!("Buffersizes smaller then 7 are not yet supported!")
    }

    // Create the <buffer_size>-bit-register
    let mut register: Vec<u8> = vec![];

    // Initialize it with 0 or 1
    for _ in 0..buffer_size {
        register.push(rand::thread_rng().gen_range(0, 2));
    }

    println!("Register: {:?}", register);

    // Shift 20 rounds
    for i in 0..20{
        // Calculate the new input-bit
        let result  = (register[0] ^ register[2]) ^ register[6];

        // Shift
        // Following code is not beautiful, but easy to understand:
        println!("Output: {}", register[0]);

        for i in 0..buffer_size - 1 {
            register[i as usize] = register[i as usize + 1];
        }
        register[buffer_size as usize - 1] = result;
        println!("Register after iteration {}: {:?}", i, register);
    }
}
