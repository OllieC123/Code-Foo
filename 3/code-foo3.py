# Code-Foo Question 3:
#
# You own a license plate manufacturing company. Write a program that takes
# a population and determines the simplest pattern that will produce enough
# unique plates. Since all the plates that match the pattern will be generated,
# find the pattern that produces the least excess plates. Use a combination of
# letters (A-Z) and numbers (0-9).


def main():
    #Input the number
    n = input('Enter a number!: ')
    #Check if input is an integer
    while n.isdigit() == False:
        n = input('Invalid number; try again!: ')

    #Define variables
    pop = int(n)
    pattern = ''
    number = 0
    letter = 0
    ttl = 0
    z = 1

    # Determine the smallest multiple of (10**x)*(26**y) [with positive integers
    # x,y where x+y = z = 1,2,3...] that is greater than n.
    # This number will be the number of total plates.
    # Excess plates = Total plates - population
    match = False
    while match == False:
        x = z
        y = 0
        while x >= 0 and match == False:
            test = (10 ** x)*(26 ** y)
            if pop <= test:
                number = x
                letter = y
                ttl = test
                match = True
            x = x-1
            y = y+1
        z = z + 1

    # Format the output of 'pattern'
    if number > 0:
        pattern = pattern + str(number) + ' number(s)'
        if letter > 0:
            pattern = pattern + ', ' + str(letter) + ' letter(s)'

    elif letter > 0:
        pattern = pattern + str(letter) + ' letter(s)'

    # Output 
    print (('Population: ') + n)
    print (('Pattern: ') + pattern)
    print (('Total Plates: ') + str(ttl))
    print (('Excess Plates: ') + str(ttl-pop))

main()
