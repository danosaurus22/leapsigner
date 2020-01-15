# leapsigner
American Sign Language to Speech Translator using LeapMotion Controller

This was a project original started by Daniel Fong. The goal was to develop an American Sign Language to speech (English) translator to help bridge the communication gap between people who are speech impaired and those who don't know ASL. 

The technology behind this was done using the LeapMotion Controller to capture hand recognition information, which was fed into a computer via high-speed USB cable, and interpreted using a software program written in Java to perform the sign-language recognition by looking at the finger positions, angles, etc. (in a polling fashion) in reference to the LeapMotion Controller's coordinate system (cartesian). After a sign had been interpreted, a java-based text-to-speech engine was used to have the computer 'speak' the word.

A Demo Video of the Project:
https://youtu.be/EMu6t4hNCII

Abstract:
In order to lower the communication barrier between the deaf and hard of hearing and the hearing population in the United States, an American Sign Language (ASL) to speech translator was developed using the Leap Motion technology. This technology captures gestural information using infrared LEDs and cameras and provides an API that allows 3rd party app development. A simple pattern-matching engine was developed to match the gestural information to a specific sign and translate that into text. This text was then fed into a text-to-speech synthesizer to generate the audible speech. A visualizer was developed to provide visual feedback to the signer as to what the Leap Motion device contextually sees. Several simple signs in ASL were successfully distinguished by the system and turned into speech, thereby successfully allow communication from the deaf population to the hearing population without the need for both parties to learn ASL. This system provides a base from which future works can expand and improve upon. One such aspect should be the integration of a communication channel allowing information to flow from the hearing population to the deaf population.

This project's visualization was adapted from Zoltan Ruzman's LeapFX project (Thank you!):
https://github.com/RuZman/LeapFX

