const parse = require('csv').parse;
const pascalCase = require('pascal-case').pascalCase;
const fs = require('fs');

function titleCase(str) {
  var splitStr = str.toLowerCase().split(' ');
  for (var i = 0; i < splitStr.length; i++) {
      // You do not need to check if i is larger than splitStr length, as your for does that for you
      // Assign it back to the array
      splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);     
  }
  // Directly return the joined string
  return splitStr.join(' '); 
}

const output = [];
const localization = {};

fs.createReadStream('./Item tracker - Cards.csv')
  .pipe(parse())
  .on('data', (data) => {
    output.push(data)
    console.log(data);
  })
  .on('end', () => {
    output.shift();
    output.forEach(([ name, type, path, picture, icon, description = '', evolution = 'null' ]) => {
      if (!(
        (name === '') ||
        (type !== 'Pokemon') ||
        (path === '') ||
        (picture === 'FALSE') ||
        (icon === 'FALSE')
      )) {
        let templateFile = fs.readFileSync('./PokemonTemplate.java', 'utf8')
  
        templateFile = templateFile.replaceAll('{{MOD_ID}}', `pokemod:${path}`);
        templateFile = templateFile.replaceAll('{{CLASS_NAME}}', pascalCase(name));
        templateFile = templateFile.replaceAll('{{PATH}}', path);
        templateFile = templateFile.replaceAll('{{EVOLUTION}}', evolution === '' ? 'null' : `new ${evolution}(this.moves)`);
        fs.writeFileSync(`./output/${pascalCase(name)}.java`, templateFile);
        console.log(templateFile);
        console.log(typeof templateFile)
        console.log(name, type, path)

        console.log(titleCase(name))
        localization[`pokemod:${path}`] = {
          NAME: titleCase(name),
          DESCRIPTION: `Go ${titleCase(name)}!`
        }
        console.log(localization)
      }
    })
  });
