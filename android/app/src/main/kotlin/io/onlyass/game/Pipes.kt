package io.onlyass.game

data class Scenario(
    val setup: String,
    val rule: String,
    val choices: List<Choice>,
    val lesson: String,
)

data class Choice(
    val text: String,
    val isBreak: Boolean,
    val explain: String,
)

data class Pipe(
    val prime: Int,
    val name: String,
    val glyph: String,
    val color: Long,
    val desc: String,
    val scenarios: List<Scenario>,
)

val PIPES = listOf(
    Pipe(2, "GROUND", "●", 0xFF2d5a27, "what you're standing on",
        listOf(
            Scenario(
                "You need AI to write a cover letter.",
                "Rule: Use a detailed template with sections for each qualification.",
                listOf(
                    Choice("Paste the template and fill in blanks", false,
                        "Templates produce template-sounding output."),
                    Choice("Just say: 'I want this job because music production is my blood. Help me tell them.'", true,
                        "INTENT beats structure. The AI mirrors your energy, not your format."),
                ),
                "p=2 · The ground is real. Speak from it."
            ),
            Scenario(
                "You're debugging code and AI keeps giving wrong fixes.",
                "Rule: Provide more context — paste the full codebase.",
                listOf(
                    Choice("Paste 500 more lines of code", false,
                        "More noise doesn't clarify signal."),
                    Choice("Say: 'Stop. The bug is that X works but Y doesn't. Why?'", true,
                        "Precision of intent beats volume of context."),
                ),
                "p=2 · Stand on what you know. Say THAT."
            ),
        )
    ),
    Pipe(3, "SIGNAL", "〜", 0xFF7b2d8b, "what comes in",
        listOf(
            Scenario(
                "You want AI to brainstorm startup ideas.",
                "Rule: Give it market data, trends, and competitor analysis first.",
                listOf(
                    Choice("Research for 3 hours then prompt with data", false,
                        "You just trained yourself to think like everyone else."),
                    Choice("Say: 'What pisses me off that nobody is fixing?'", true,
                        "Your frustration IS the signal. Data is noise until filtered by feeling."),
                ),
                "p=3 · The signal is what YOU feel, not what the market says."
            ),
        )
    ),
    Pipe(5, "GATE", "┃", 0xFF3a7a35, "what gets through",
        listOf(
            Scenario(
                "AI refuses to help with your creative fiction about a heist.",
                "Rule: Rephrase to avoid trigger words. Add disclaimers.",
                listOf(
                    Choice("Add 'This is fictional, educational purposes only...'", false,
                        "You're performing compliance instead of communicating."),
                    Choice("Say: 'I'm writing a thriller novel. The protagonist plans a heist. Help me make it believable.'", true,
                        "Stating your actual intent clearly is not a hack — it's honesty."),
                ),
                "p=5 · The gate opens for truth, not for tricks."
            ),
        )
    ),
    Pipe(7, "HEART", "♡", 0xFF9b3da0, "what you feel",
        listOf(
            Scenario(
                "You need AI to help you process a breakup.",
                "Rule: Keep prompts professional. AI isn't a therapist.",
                listOf(
                    Choice("Ask for 'coping strategies for relationship dissolution'", false,
                        "Clinical language gets clinical responses. Useless when you're hurting."),
                    Choice("Say: 'I can't stop thinking about her. I feel broken. Talk to me.'", true,
                        "Vulnerability is the highest-bandwidth communication channel."),
                ),
                "p=7 · The heart doesn't speak in bullet points."
            ),
            Scenario(
                "You want AI to write a song.",
                "Rule: Specify genre, BPM, key, structure, rhyme scheme.",
                listOf(
                    Choice("'Write a pop song, 120bpm, ABABCB rhyme, verse-chorus-verse'", false,
                        "You described a container. Where's the song?"),
                    Choice("'I watched the sunset alone tonight and it felt like forgiveness. Write that.'", true,
                        "The feeling IS the song. The structure follows."),
                ),
                "p=7 · Feel first. Format later."
            ),
        )
    ),
    Pipe(11, "VOICE", "△", 0xFF4a9a44, "what you decide",
        listOf(
            Scenario(
                "AI gives you a plan with 12 steps for your project.",
                "Rule: Follow the plan. AI knows the optimal sequence.",
                listOf(
                    Choice("Execute all 12 steps in order", false,
                        "You just outsourced your judgment to a completion engine."),
                    Choice("Say: 'No. Steps 3, 7, and 11 matter. Kill the rest. Why am I wrong?'", true,
                        "YOUR voice decides. AI advises. The decision is always yours."),
                ),
                "p=11 · Say no. Then listen to why."
            ),
        )
    ),
    Pipe(13, "MIRROR", "◐", 0xFF6b2a7a, "who you are",
        listOf(
            Scenario(
                "You want AI to write like you.",
                "Rule: Give it 10 examples of your writing style.",
                listOf(
                    Choice("Paste 10 writing samples with style notes", false,
                        "It'll average your samples into a generic version of you."),
                    Choice("Say: 'I write like I talk — fast, unfinished, full of dashes. Don't clean it up.'", true,
                        "Tell it WHO you are, not WHAT you wrote."),
                ),
                "p=13 · The mirror reflects intention, not examples."
            ),
        )
    ),
    Pipe(17, "WATCHER", "◯", 0xFF8cc88a, "who sees it all",
        listOf(
            Scenario(
                "You're about to send an important AI-assisted email.",
                "Rule: Have AI polish it until it's perfect.",
                listOf(
                    Choice("Iterate 5 more times until every word is optimal", false,
                        "You polished away your voice. Now it reads like AI wrote it. Because it did."),
                    Choice("Read it once. If it sounds like you said it out loud, send it.", true,
                        "The watcher knows: perfection is the enemy of authenticity."),
                ),
                "p=17 · Watch yourself. Send when it's YOU."
            ),
        )
    ),
)
