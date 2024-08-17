/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/templates/**/*.html",
       "./src/main/resources/static/**/*.js",
  ],
  theme: {
      extend: {
        colors: {
          'custom-dark': '#2E3930',
          'dark-yellow':'#C6A479',// Add your custom color
        },
      },
    },
  plugins: [],
}

