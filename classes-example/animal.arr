# animal.arr

exports = fn(genus, species) {
    local:this = any()

    this.genus = genus
    this.species = species
    this.printTaxonomy = fn() {
        print("This animal is a ${this.genus} ${this.species}.")
    }

    ret this
}